package cn.minsin.core.web.activetable;

import cn.minsin.core.constant.MessageConstant;

import java.lang.annotation.*;

/**
 * 动态表格注解
 * @author: minton.zhang
 * @since: 2019/10/14 19:38
 */
@Target({ElementType.FIELD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Th {

    String value() default MessageConstant.NULL;
}
