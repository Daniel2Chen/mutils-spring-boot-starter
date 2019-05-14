/**
 *
 */
package cn.minsin.base;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.util.Assert;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

import cn.minsin.annotation.LogicDelete;
import cn.minsin.annotation.PrimaryKey;
import cn.minsin.core.exception.MutilsErrorException;
import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.tools.ListUtil;
import cn.minsin.core.tools.ModelUtil;
import cn.minsin.core.tools.StringUtil;


public class BaseRepositoryImpl<T, ID> extends SimpleMongoRepository<T, ID> implements BaseRepository<T, ID> {
	
	final String PLACE_HOLDER="_obj";

	private final MongoOperations mongotemplate;

	private final MongoEntityInformation<T, ID> entityInformation;

	public BaseRepositoryImpl(MongoEntityInformation<T, ID> metadata, MongoOperations mongoOperations) {
		super(metadata, mongoOperations);
		this.mongotemplate = mongoOperations;
		this.entityInformation = metadata;
	}

	@Override
	public long updateOne(Query query, Update update) {
		UpdateResult updateFirst = mongotemplate.updateFirst(query, update, entityInformation.getJavaType());
		return updateFirst.getModifiedCount();
	}

	@Override
	public long updateMany(Query query, Update update) {
		UpdateResult updateMulti = mongotemplate.updateMulti(query, update, entityInformation.getJavaType());
		return updateMulti.getModifiedCount();
	}

	@Override
	public MongoTemplate getMongotemplate() {
		return (MongoTemplate) mongotemplate;
	}

	@Override
	public long delete(Query query) {
		DeleteResult remove = mongotemplate.remove(query, entityInformation.getJavaType());
		return remove.getDeletedCount();
	}

	@Override
	public List<Object> selectSingleField(Query query, String field) {
		List<Object> obj = new ArrayList<>();
		try {
			@SuppressWarnings("rawtypes")
			List<Map> find = mongotemplate.find(
					new BasicQuery(query == null ? new Document() : query.getQueryObject(),
							new Document().append(field, true).append("_id", false)),
					Map.class, entityInformation.getCollectionName());
			find.forEach(e -> {
				Object object = e.get(field);
				if (object != null) {
					obj.add(object);
				}
			});
			return obj;
		} catch (Exception e) {
			throw new MutilsException(e);
		}
	}

	@Override
	public Page<T> selectAll(Query query, PageRequest pageRequest) {
		Pageable pageable = pageRequest == null ? Pageable.unpaged() : pageRequest;
		query.with(pageable);
		List<T> list = mongotemplate.find(query, entityInformation.getJavaType(),
				entityInformation.getCollectionName());
		return PageableExecutionUtils.getPage(list, pageable, () -> mongotemplate.count(query,
				entityInformation.getJavaType(), entityInformation.getCollectionName()));
	}

	/**
	 * 按照@primaryKey列进行查询数据
	 */
	@Override
	public T selectByPrimaryKey(String value) {
		Class<T> javaType = entityInformation.getJavaType();
		String id = null;
		Set<Field> fields = ModelUtil.getAllFields(javaType);
		for (Field field : fields) {
			PrimaryKey annotation = field.getAnnotation(PrimaryKey.class);
			if (annotation != null) {
				id = field.getName();
				break;
			}
		}
		return mongotemplate.findOne(new Query(BaseCriteria.where(id).is(value)), javaType);
	}

	@Override
	public T selectOne(Query query) {
		return mongotemplate.findOne(query, entityInformation.getJavaType());
	}

	@Override
	public long count(Query query) {
		return mongotemplate.count(query, entityInformation.getJavaType());
	}

	@Override
	public List<T> selectList(Query query, PageRequest pageRequest) {
		Pageable pageable = pageRequest == null ? Pageable.unpaged() : pageRequest;
		query.with(pageable);
		return mongotemplate.find(query, entityInformation.getJavaType(), entityInformation.getCollectionName());
	}

	@Override
	public Page<T> buliderPage(List<T> list, Query query, PageRequest pageRequest) {
		Pageable pageable = pageRequest == null ? Pageable.unpaged() : pageRequest;
		query.with(pageable);
		return PageableExecutionUtils.getPage(list, pageable, () -> mongotemplate.count(query,
				entityInformation.getJavaType(), entityInformation.getCollectionName()));
	}

	@Override
	public long insertSelective(T model, boolean autoPrimaryKey) {
		try {
			Document document = new Document();
			Set<Field> fields = ModelUtil.getAllFields(model);
			for (Field field : fields) {
				if (ModelUtil.verificationField(field)) {
					continue;
				}
				String key = field.getName();
				field.setAccessible(true);
				Object value = field.get(model);
				LogicDelete delete = field.getAnnotation(LogicDelete.class);
				if (delete != null) {
					value = delete.valid();
				}
				PrimaryKey annotation = field.getAnnotation(PrimaryKey.class);
				if (annotation != null) {
					if (autoPrimaryKey) {
						value = StringUtil.getUUIDForLength(32);
					}
					if (key == null || value == null) {
						throw new MutilsErrorException("@PrimaryKey所属字段Value为空 ");
					}
					document.put("_id", value);
					document.put(key, value);
				} else {
					if (StringUtil.isBlank(value)) {
						value = null;
					}
					document.put(key, value);
				}
			}
			mongotemplate.insert(document, entityInformation.getCollectionName());
			return 1;
		} catch (Exception e) {
			throw new MutilsException(e);
		}
	}

	@Override
	public long updateByPrimaryKeySelective(T model, boolean Ignore) {
		try {
			Object idValue = null;
			String idkey = null;
			Set<Field> fields = ModelUtil.getAllFields(model);
			Update update = new Update();
			for (Field field : fields) {
				if (ModelUtil.verificationField(field)) {
					continue;
				}
				PrimaryKey annotation = field.getAnnotation(PrimaryKey.class);

				String key = field.getName();

				// 不能修改_id列
				boolean pass = ("_id".equals(key) && annotation == null);

				if (!pass) {
					field.setAccessible(true);
					Object value = field.get(model);
					if (annotation != null && idValue == null && idkey == null) {
						if (StringUtil.isBlank(value)) {
							throw new MutilsErrorException("@PrimaryKey所属字段Value为空");
						}
						idValue = value;
						idkey = key;
					} else {
						if (StringUtil.isBlank(value) && Ignore) {
							continue;
						}
						update.set(key, value);
					}
				}
			}
			UpdateResult updateFirst = mongotemplate.updateFirst(new Query(BaseCriteria.where(idkey).is(idValue)),
					update, entityInformation.getJavaType());
			return updateFirst.getModifiedCount();

		} catch (Exception e) {
			throw new MutilsException(e);
		}
	}

	@Override
	public long logicDeleteByPrimaryKey(T model, boolean logicRealValue) {
		try {
			Set<Field> fields = ModelUtil.getAllFields(model);

			Field idField = null;
			Field delField = null;
			Object invalid = 1;
			for (Field field : fields) {
				if (ModelUtil.verificationField(field)) {
					continue;
				}
				PrimaryKey annotation = field.getAnnotation(PrimaryKey.class);
				if (annotation != null) {
					idField = field;
					continue;
				}
				LogicDelete delete = field.getAnnotation(LogicDelete.class);
				if (delete != null) {
					delField = field;
					if (logicRealValue) {
						delField.setAccessible(true);
						invalid = delField.get(model);
						if (StringUtil.isBlank(invalid)) {
							throw new MutilsErrorException("@LogicDelete所属字段Value为空");
						}
					} else {
						invalid = delete.invalid();
					}
					continue;
				}
				if (idField != null && delField != null) {
					break;
				}
			}
			// 获取值
			idField.setAccessible(true);
			Object idValue = idField.get(model);
			if (idValue != null) {
				Update update = new Update();
				update.set(delField.getName(), invalid);
				UpdateResult updateFirst = mongotemplate.updateFirst(
						new Query(BaseCriteria.where(idField.getName()).is(idValue)), update,
						entityInformation.getJavaType());
				return updateFirst.getModifiedCount();
			}
			return 0;
		} catch (Exception e) {
			throw new MutilsException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <O> O aggregation(Criteria match, String key, String value, Class<O> output, GroupType type) {
		try {
			Aggregation newAggregation = Aggregation.newAggregation(Aggregation.match(match),
					this.groupOperation(key, value, type));
			AggregationResults<Document> aggregationResults = mongotemplate.aggregate(newAggregation,
					entityInformation.getCollectionName(), Document.class);
			// 获取结果集
			List<Document> documentList = aggregationResults.getMappedResults();
			if (!ListUtil.isEmpty(documentList)) {
				// 获取结果的第一个结果
				return (O) documentList.get(0).get(PLACE_HOLDER);
			}
			return null;
		} catch (Exception e) {
			throw new MutilsException(e);
		}
	}

	protected GroupOperation groupOperation(String key1, String key2, GroupType type) {
		Assert.notNull(type, "GroupType 不能为空");
		switch (type) {
		case AVG:
			return Aggregation.group(key1).avg(key2).as(PLACE_HOLDER);
		case MAX:
			return Aggregation.group(key1).max(key2).as(PLACE_HOLDER);
		case MIN:
			return Aggregation.group(key1).min(key2).as(PLACE_HOLDER);
		case SUM:
			return Aggregation.group(key1).sum(key2).as(PLACE_HOLDER);
		case COUNT:
			return Aggregation.group(key1).count().as(PLACE_HOLDER);
		case FIRST:
			return Aggregation.group(key1).first(key2).as(PLACE_HOLDER);
		case LAST:
			return Aggregation.group(key1).last(key2).as(PLACE_HOLDER);
		default:
			return null;
		}
	}
}
