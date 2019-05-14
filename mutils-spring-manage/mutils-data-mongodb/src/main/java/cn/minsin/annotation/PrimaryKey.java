/**
 * 
 */
package cn.minsin.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.data.annotation.Id;

/**
 * 	用于标识主键  可以与{@link Id} 同时存在于一个对象
 * @author minton.zhang
 * 2018年10月23日
 */
@Target({ElementType.FIELD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface PrimaryKey {

}
