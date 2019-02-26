package cn.minsin.core.tools;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import cn.minsin.core.annotation.NotNull;
import cn.minsin.core.exception.MutilsErrorException;
import cn.minsin.core.exception.MutilsException;

/**
 * mutils中对实体类的一个转换工具类
 *	 需要配合 {@link NotNull} 注解使用
 * @author mintonzhang
 * @date 2019年2月18日
 * @since 0.3.4
 */
public class ModelUtil {
	
	private static final String ERROR_MESSAGE_TEMPLATE = " '%s' Can't be empty,model field means '%s'";
	
	public static <T> SortedMap<String, String> toTreeMap(T model) throws MutilsErrorException {
		boolean flag = false;
		SortedMap<String, String> tree = new TreeMap<>();
		for (Field field : getAllFields(model)) {
			if (verificationField(field)) {
				continue;
			}
			NotNull annotation = field.getAnnotation(NotNull.class);
			try {
				String key =annotation.key();
				if(StringUtil.isBlank(key)) {
					key = field.getName();
				}
				field.setAccessible(true);
				Object object = field.get(model);
				if (annotation != null && annotation.notNull()) {
					if (StringUtil.isBlank(object)) {
						String description = annotation.value();
						throw new MutilsException(String.format(ERROR_MESSAGE_TEMPLATE, key, description));
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

	public static <T> Set<Field> getAllFields(T model) {
		Set<Field> hashset = new HashSet<>();
		Class<?> clazz = model.getClass();
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
	public static <T> void verificationField(T model) {
		for (Field field : getAllFields(model)) {
			if (verificationField(field)) {
				continue;
			}
			NotNull annotation = field.getAnnotation(NotNull.class);
			if (annotation != null && annotation.notNull()) {
				try {
					String key = field.getName();
					field.setAccessible(true);
					Object object = field.get(model);
					if (StringUtil.isBlank(object)) {
						String description = annotation.value();
						throw new MutilsException(String.format(ERROR_MESSAGE_TEMPLATE, key, description));
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
	public static boolean verificationField(Field field) {
		int modifiers = field.getModifiers();
		if (Modifier.isStatic(modifiers) || !Modifier.isPrivate(modifiers) || Modifier.isFinal(modifiers)) {
			return true;
		}
		return false;
	}
}
