package cn.minsin.core.web.annotation;

import cn.minsin.core.web.result.OperationType;

import java.lang.annotation.*;


/**
 * 用于切面自动判断
 * 请使用 {@link OperationType}或其子接口
 * @author minsin
 * @since 0.0.8.RELEASE
 */
@Target({ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface TryException {

    /**
     * 操作类型 {@link TryException#autoChooseKey() }优先级高于value
     * 请使用OperationType下的操作类型
     *
     * @return
     */
    String value() default OperationType.DO;

    /**
     * 自动选择的判断key
     *
     * @return
     */
    String autoChooseKey() default "";

}
