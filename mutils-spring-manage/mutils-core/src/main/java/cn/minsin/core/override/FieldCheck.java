package cn.minsin.core.override;

import cn.minsin.core.annotation.NotNull;
import cn.minsin.core.constant.MessageConstant;
import cn.minsin.core.tools.ModelUtil;
import cn.minsin.core.web.Result;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Set;

/**
 * 字段检查 配合{@link NotNull}
 * @author: minton.zhang
 * @since: 2019/11/4 16:38
 */
public interface FieldCheck extends Serializable {


    /**
     * 默认数据字段检查
     *
     * @return
     */
    default Result verificationThenToResult() {

        Set<Field> allFields = ModelUtil.getAllFields(this);

        for (Field field : allFields) {
            NotNull annotation = field.getAnnotation(NotNull.class);
            if (annotation != null && annotation.notNull()) {
                try {
                    field.setAccessible(true);
                    Object object = field.get(this);
                    if (object == null) {
                        return Result.missParam(String.format(MessageConstant.NOT_NULL_MESSAGE, annotation.value()));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return Result.missParam(String.format(MessageConstant.UNKNOWN_EXCEPTION_MESSAGE, annotation.value()));
                }
            }
        }
        return null;
    }

    /**
     * 验证字段
     */
    default void verificationField() {
        ModelUtil.verificationField(this);
    }


}
