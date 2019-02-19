/**
 * 
 */
package cn.minsin.core.web;

import java.io.Serializable;
import java.util.HashMap;

import com.alibaba.fastjson.JSON;

import cn.minsin.core.exception.MutilsException;

/**
 * 	动态构建Vo对象 替代的实体类中的VO
 * @author mintonzhang
 * @date 2019年2月14日
 * @since 0.1.0
 */
public class VO extends HashMap<String, Object> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6208106294465026862L;

	protected VO() {
	}

	public static VO init() {
		return new VO();
	}

	public static VO init(String key, Object value) {
		return new VO().put(key, value);
	}

	@Override
	public VO put(String key, Object value) {
		super.put(key,value);
		return this;
	}

	public VO remove(String key) {
		super.remove(key);
		return this;
	}

	public VO removeAll() {
		super.clear();
		return this;
	}

	public Object getValue(String key) {
		return super.get(key);
	}

	/**
	 * 	转换成指定对象 必须实现序列化
	 * @param clazz
	 * @return 指定Class
	 */
	public <T extends Serializable> T toObject(Class<T> clazz) {
		try {
			return JSON.parseObject(toString(), clazz);
		} catch (Exception e) {
			throw new MutilsException(e, "类型转换失败");
		}
	}
	

	@Override
	public boolean isEmpty() {
		return super.isEmpty();
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
