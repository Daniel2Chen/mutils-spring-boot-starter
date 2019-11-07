package cn.minsin.core.web.table;

import cn.minsin.core.constant.MessageConstant;

import java.lang.annotation.*;

/**
 * 动态表格注解
 * @author: minton.zhang
 * @since: 0.0.8.RELEASE
 */
@Target({ElementType.FIELD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Th {

    String value() default MessageConstant.NULL;
}
