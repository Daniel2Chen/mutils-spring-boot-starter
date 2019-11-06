package cn.minsin.core.tools;

import cn.minsin.core.annotation.Ignore;
import cn.minsin.core.annotation.NotNull;
import cn.minsin.core.constant.MessageConstant;
import cn.minsin.core.exception.MutilsException;
import lombok.Getter;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * mutils中对实体类的一个转换工具类
 * 需要配合 {@link NotNull} 注解使用
 *
 * @author mintonzhang
 * @date 2019年2月18日
 * @since 0.3.4
 */
public class ModelUtil {
    protected ModelUtil() {
        // allow Subclass
    }

    private static final String ERROR_MESSAGE_TEMPLATE = " '%s' Can't be empty,model field means '%s'";

    public static <T> SortedMap<String, String> toTreeMap(T model) {
        SortedMap<String, String> tree = new TreeMap<>();
        for (Field field : getAllFieldsAndFilter(model)) {
            ParseFiled parseFiled = parseFiled(field, model);
            if (parseFiled.isNotNull()) {
                tree.put(parseFiled.getKey(), parseFiled.getStringValue());
            }
        }
        return tree;
    }

    /**
     * 获取所有字段 包括父类
     *
     * @param model
     * @param <T>
     * @return
     */
    public static <T> Set<Field> getAllFields(T model) {
        Set<Field> fieldSet = new HashSet<>();
        //第一个superClass
        Class<?> superClass = model.getClass().getSuperclass();

        //如果父类不是Object才进行添加
        if (superClass != null && !superClass.equals(Object.class)) {
            while (superClass != null) {
                Field[] fields = superClass.getDeclaredFields();
                fieldSet.addAll(Arrays.asList(fields));
                superClass = superClass.getSuperclass();
            }
        }
        Class<?> clazz = model.getClass();
        Field[] declaredFields = clazz.getDeclaredFields();
        fieldSet.addAll(Arrays.asList(declaredFields));
        return fieldSet;
    }

    /**
     * 获取所有字段 包括父类
     *
     * @param model
     * @param <T>
     * @return
     */
    public static <T> Set<Field> getAllFieldsAndFilter(T model) {
        Set<Field> allFields = getAllFields(model);
        allFields.removeIf(ModelUtil::verificationField);
        return allFields;
    }

    /**
     * 验证字段
     */
    public static <T> void verificationField(T model) throws MutilsException {
        for (Field field : getAllFieldsAndFilter(model)) {
            ParseFiled parseFiled = parseFiled(field, model);
            MutilsException.throwException(parseFiled.isNull(), MessageConstant.UNFILLED_ITEM);
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
        Ignore ignore = field.getAnnotation(Ignore.class);
        return Modifier.isStatic(modifiers)
                || Modifier.isTransient(modifiers)
                || Modifier.isFinal(modifiers)
                || Modifier.isNative(modifiers)
                || !Modifier.isPrivate(modifiers)
                || ignore != null;
    }


    /**
     * 初始书字段
     */
    @Getter
    public static class ParseFiled {

        private Object value;

        private String key;

        public boolean isNotNull() {
            return value != null;
        }
        public boolean isNull() {
            return value == null;
        }

        public String getStringValue() {
            return isNotNull() ? value.toString() : null;
        }

        void setData(Object value, String key) {
            this.value = value;
            this.key = key;
        }
    }

    public static <T> ParseFiled parseFiled(Field field, T model) {
        ParseFiled parseFiled = new ParseFiled();
        try {
            NotNull annotation = field.getAnnotation(NotNull.class);
            String key = annotation.key();
            if (StringUtil.isBlank(key)) {
                key = field.getName();
            }
            field.setAccessible(true);
            Object object = field.get(model);
            if (annotation.notNull() && object == null) {
                String description = annotation.value();
                throw new MutilsException(String.format(ERROR_MESSAGE_TEMPLATE, key, description));
            }
            parseFiled.setData(object, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return parseFiled;
    }
}
