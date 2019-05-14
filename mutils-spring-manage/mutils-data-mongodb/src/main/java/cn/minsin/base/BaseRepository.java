/**
 * 
 */
package cn.minsin.base;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import cn.minsin.annotation.LogicDelete;
import cn.minsin.annotation.PrimaryKey;

/**
 * @author mintonzhang 2018年10月15日
 */
@NoRepositoryBean
public interface BaseRepository<T, ID> extends MongoRepository<T, ID> {

	/**
	 * 按照条件和分页查询数据
	 * 
	 * @param query
	 * @param pageRequest 是 Pageable 的子类 提供了快速构建方法
	 */
	Page<T> selectAll(Query query, PageRequest pageRequest);

	/**
	 * 修改 逻辑删除也是调用这个方法
	 * 
	 * @param query
	 * @param update
	 */
	long updateOne(Query query, Update update);

	/**
	 * 修改多个
	 * 
	 * @param query
	 * @param update
	 */
	long updateMany(Query query, Update update);

	/**
	 * 按照条件删除
	 * 
	 * @param query
	 */
	long delete(Query query);

	/**
	 * 获取mongoTempalte
	 */
	MongoTemplate getMongotemplate();

	/**
	 * 
	 * 查询单个字段(替换之前的子查询) BaseCriteria 提供模糊查询区间段查询notnull 和isNotNull相关方法
	 * 
	 * @param query 查询条件语句
	 * @param field 要查询的字段
	 */
	List<Object> selectSingleField(Query criteria, String field);

	/**
	 * 按照条件查询一个对象
	 * 
	 * @param query
	 */
	T selectOne(Query query);

	/**
	 * 查询总条数
	 * 
	 * @param query
	 */
	long count(Query query);

	/**
	 * 配合buliderPage 使用
	 * 
	 * @param query
	 * @param pageRequest
	 */
	List<T> selectList(Query query, PageRequest pageRequest);

	/**
	 * 配合find使用
	 * 
	 * @param list
	 * @param query
	 * @param pageRequest
	 */
	Page<T> buliderPage(List<T> list, Query query, PageRequest pageRequest);


	/**
	 * 	动态添加对象到指定集合
	 * 	如果带有逻辑删除的字段{@link LogicDelete} 默认获取 valid作为值存入数据库
	 * @param model 预添加对象
	 * @param autoPrimaryKey 是否自动生成主键 如果为否时，代用{@link PrimaryKey} 的字段不能为空
	 * @return
	 */
	long insertSelective(T model, boolean autoPrimaryKey);

	/**
	 * 	可变修改 
	 * @param model 预修改对象
	 * @param IgnoreNull 是否忽略空值
	 * @return
	 */
	long updateByPrimaryKeySelective(T model, boolean IgnoreNull);

	/**
	 * 根据主键id 查找某个实体类 注意：实体类的主键id必须加 {@link PrimaryKey} 注解
	 * 
	 * @param id
	 */
	T selectByPrimaryKey(String id);

	/**
	 * 逻辑删除
	 * 
	 * @param model
	 * @return
	 */
	long logicDeleteByPrimaryKey(T model,boolean logicRealValue);
	
	/**
	 * 	聚合类型
	 * @param <O> 返回值类型
	 * @param match where条件
	 * @param key 需要分组的字段
	 * @param valu 需要聚合的字段
	 * @param output 返回值类型
	 * @param type 聚合类型
	 * @return
	 */
	<O> O aggregation(Criteria match,String key,String value,Class<O> output,GroupType type);

}
