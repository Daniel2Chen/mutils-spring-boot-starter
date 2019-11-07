package cn.minsin.core.web.override;

import cn.minsin.core.annotation.NotNull;
import cn.minsin.core.constant.MessageConstant;
import cn.minsin.core.override.FieldCheck;
import cn.minsin.core.tools.ModelUtil;
import cn.minsin.core.web.result.Result;

import java.lang.reflect.Field;
import java.util.Set;

/**
 * @author: minton.zhang
 * @since: 0.0.8.RELEASE
 */
public interface WebFieldCheck extends FieldCheck {
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

}
