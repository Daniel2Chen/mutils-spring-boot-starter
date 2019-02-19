package cn.minsin.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNull {
	
	 String value() default "Field cannot be empty";
	 /**
	  * 新增虚拟key
	  * 用于返回实体类的key 在0.3.4之前是用实体类的属性名作为key
	  * since 0.3.4
	  * @return 
	  */
	 String key() default "";
	 boolean notNull() default  true;
	 


}
