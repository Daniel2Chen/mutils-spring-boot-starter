/**
 * 
 */
package cn.minsin.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 逻辑删除
 * 
 * @author minton.zhang
 * @date 2019年5月7日
 * @since 0.0.2.RELEASE
 */
@Target({ ElementType.FIELD })
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface LogicDelete {

	/**
	 * 删除时的标识
	 * 
	 * @return
	 */
	int invalid() default 1;

	/**
	 * 有效时的标识
	 * 
	 * @return
	 */
	int valid() default 0;

}
