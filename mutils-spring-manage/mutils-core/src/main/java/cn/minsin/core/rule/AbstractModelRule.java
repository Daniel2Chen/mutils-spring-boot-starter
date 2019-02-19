package cn.minsin.core.rule;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

/**
 * 第三方接口所需要继承的父类 这个抽象内不能定义字段
 * 
 * @author minsin
 *
 */
public abstract class AbstractModelRule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 57625408003186203L;

	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	@Override
	public String toString() {
		//移除校验 since 0.2.7
		return JSON.toJSONString(this);
	}
}
