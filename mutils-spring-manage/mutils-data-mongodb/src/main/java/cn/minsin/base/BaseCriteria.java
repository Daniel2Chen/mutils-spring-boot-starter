/**
 * 
 */
package cn.minsin.base;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import org.bson.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.CriteriaDefinition;

import cn.minsin.core.tools.StringUtil;

/**
 * CriteriaDefinition的实现类 继承自Criteria
 * 
 * <pre style="color:red">
 * 因为继承关系是单向的 某些情况使用时需要混合调用方法
 * (即使用Criteria中的方法和BaseCriteria的方法)
 * 必须要先调用BaseCriteria的方法然后在调用Criteria
 * </pre>
 * @author mintonzhang
 * @since 0.0.2.RELEASE
 */
public class BaseCriteria extends Criteria implements CriteriaDefinition {

	private BaseCriteria(String key) {
		super(key);
	}

	public BaseCriteria() {
		super();
	}

	public static BaseCriteria where(String key) {
		return new BaseCriteria(key);
	}

	/**
	 * 等价key%
	 * 
	 * @param key
	 */
	public BaseCriteria likeEnd(String key) {
		Pattern compile = Pattern.compile("^.*" + key + "$", Pattern.CASE_INSENSITIVE);
		super.regex(compile);
		return this;
	}

	/**
	 * 等价%key
	 * 
	 * @param key
	 */
	public BaseCriteria likeStart(String key) {
		Pattern compile = Pattern.compile("^" + key + ".*$", Pattern.CASE_INSENSITIVE);
		super.regex(compile);
		return this;
	}

	/**
	 * 等价%key%
	 * 
	 * @param key
	 */
	public BaseCriteria like(String key) {
		Pattern compile = Pattern.compile("^.*" + key + ".*$", Pattern.CASE_INSENSITIVE);
		super.regex(compile);
		return this;
	}

	/**
	 * 区间查询
	 * 
	 * @param data1   较小数据
	 * @param data2   较大数据
	 * @param isEqual 是否等于
	 */
	public BaseCriteria between(Object data1, Object data2, boolean isEqual) {
		if (data1 == null && data2 == null) {
			return this;
		}
		if (isEqual) {
			if (data1 != null)
				super.gte(data1);
			if (data2 != null)
				super.lte(data2);
		} else {
			if (data1 != null)
				super.gt(data1);
			if (data2 != null)
				super.lt(data2);
		}
		return this;
	}

	/**
	 * 查询当天的数据
	 * 
	 * @return 2018年10月17日
	 */
	public BaseCriteria today() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		Date todayStart = calendar.getTime();
		calendar.add(Calendar.DATE, 1);
		Date endStart = calendar.getTime();
		super.gte(todayStart).lte(endStart);
		return this;
	}

	public BaseCriteria isNotNull() {
		super.ne(null);
		return this;
	}

	public BaseCriteria isNull() {
		super.is(null);
		return this;
	}

	@Override
	public Document getCriteriaObject() {
		return super.getCriteriaObject();
	}

	@Override
	public String getKey() {
		return super.getKey();
	}

	public static boolean exits(Criteria criteria, String key) {
		if (criteria != null && StringUtil.isNotBlank(key)) {
			return criteria.getCriteriaObject().containsKey(key);
		}
		return false;
	}

	public static boolean notExits(Criteria criteria, String key) {
		return !exits(criteria, key);
	}
}
