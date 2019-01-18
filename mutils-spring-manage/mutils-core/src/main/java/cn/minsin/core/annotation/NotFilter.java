/**
 *
 */
package cn.minsin.core.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 	用于切面
 * @author mintonzhang
 * @date 2019年1月18日
 * @since 0.2.5
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface NotFilter {
    boolean isFilter() default true;
}
