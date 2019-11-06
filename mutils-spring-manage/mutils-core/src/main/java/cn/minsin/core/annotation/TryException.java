package cn.minsin.core.annotation;

import cn.minsin.core.web.DefaultOperationType;

import java.lang.annotation.*;


/**
 * 用于切面自动判断
 * @author mintonzhang
 * @date 2019年11月2日
 */
@Target({ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface TryException {

    /**
     * 操作类型 {@link TryException#autoChooseKey() }优先级高于value
     *
     * @return
     */
    DefaultOperationType value() default DefaultOperationType.DO;

    /**
     * 自动选择的判断key
     *
     * @return
     */
    String autoChooseKey() default "";
}
