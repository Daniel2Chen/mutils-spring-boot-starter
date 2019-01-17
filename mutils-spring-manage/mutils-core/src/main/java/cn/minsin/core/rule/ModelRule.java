package cn.minsin.core.rule;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

import cn.minsin.core.annotation.NotNull;
import cn.minsin.core.exception.MutilsErrorException;
import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.tools.StringUtil;

/**
 * 第三方接口所需要继承的父类 这个抽象内不能定义字段
 * 
 * @author minsin
 *
 */
public abstract class ModelRule implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 57625408003186203L;

	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	private final String mssage = " '%s' Can't be empty,This field means '%s'";

	@Override
	public String toString() {
		verificationField();
		return JSON.toJSONString(this);
	}

	protected SortedMap<String, String> toTreeMap() throws MutilsErrorException {
		boolean flag = false;
		SortedMap<String, String> tree = new TreeMap<>();
		for (Field field : getAllFields()) {
			if (verificationField(field))
				continue;
			NotNull annotation = field.getAnnotation(NotNull.class);
			try {
				String key = field.getName();
				field.setAccessible(true);
				Object object = field.get(this);
				if (annotation != null && annotation.notNull()) {
					if (StringUtil.isBlank(object)) {
						String description = annotation.value();
						throw new MutilsException(String.format(mssage, key, description));
					} 
				}
				if (!StringUtil.isBlank(object)) {
					tree.put(key, object.toString());
				}
			} catch (Exception e) {
				flag = true;
				e.printStackTrace();
				continue;
			}
		}
		if (flag) {
			throw new MutilsErrorException("Some fields is null.Program termination");
		}
		return tree;
	}

	protected Set<Field> getAllFields() {
		Set<Field> hashset = new HashSet<>();
		Class<?> clazz = this.getClass();
		while (true) {
			if (clazz == null) {
				break;
			}
			Field[] fields = clazz.getDeclaredFields();
			hashset.addAll(Arrays.asList(fields));
			clazz = clazz.getSuperclass();
		}
		return hashset;
	}

	/**
	 * 验证字段
	 */
	public void verificationField() {
		for (Field field : getAllFields()) {
			if (verificationField(field))
				continue;
			NotNull annotation = field.getAnnotation(NotNull.class);
			if (annotation != null && annotation.notNull()) {
				try {
					String key = field.getName();
					field.setAccessible(true);
					Object object = field.get(this);
					if (StringUtil.isBlank(object)) {
						String description = annotation.value();
						throw new MutilsException(String.format(mssage, key, description));
					}
				} catch (Exception e) {
					e.printStackTrace();
					continue;
				}
			}
		}
	}

	/**
	 * 验证某个字段
	 * 
	 * @param field
	 * @return
	 */
	protected boolean verificationField(Field field) {
		int modifiers = field.getModifiers();
		if (Modifier.isStatic(modifiers) || !Modifier.isPrivate(modifiers) || Modifier.isFinal(modifiers)) {
			return true;
		}
		return false;
	}
}
