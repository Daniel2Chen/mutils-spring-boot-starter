package cn.minsin.aop.core;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import cn.minsin.aop.annotation.LoggerRecord;
import cn.minsin.core.rule.AbstractModelRule;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoggerModel extends AbstractModelRule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 581358877935345167L;

	private String clazzName;

	private String methodName;

	private Map<String, Object> params = new HashMap<>();

	private String log;

	private Object returnVal;

	private String returnValClass;

	@Override
	public String toString() {
		return super.toString();
	}

	public LoggerModel(Object returnValue, Method method, Object[] args, Object target) {
		super();
		this.init(returnValue, method, args, target);

	}

	private void init(Object returnValue, Method method, Object[] args, Object target) {
		this.returnVal = returnValue;
		this.returnValClass = returnValue == null ? "" : returnValue.getClass().getName();
		this.methodName = method.getName();
		if (args != null) {
			for (int i = 0; i < args.length; i++) {
				params.put("paramsIndex: " + i, args[i] == null ? "" : args[i]);
			}
		}
		LoggerRecord annotation = method.getAnnotation(LoggerRecord.class);
		if (annotation != null && annotation.isDo()) {
			this.log = annotation.value();
		}
		this.clazzName = target.getClass().getName();
	}
}
