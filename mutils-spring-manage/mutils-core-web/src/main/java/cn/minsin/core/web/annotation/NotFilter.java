/**
 *
 */
package cn.minsin.core.web.annotation;

import java.lang.annotation.*;

/**
 * 	用于切面，
 * 	一般是用于controller接口是否能被切面拦截，使用该注解后，在切面中进行判断。
 * @author mintonzhang
 * @date 2019年1月18日
 * @since 0.2.5
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface NotFilter {
	/**
	 * 	是否过滤，默认true
	 * @return
	 */
    boolean value() default true;
}
