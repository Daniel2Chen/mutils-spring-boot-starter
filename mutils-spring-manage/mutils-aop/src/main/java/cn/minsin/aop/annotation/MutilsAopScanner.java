package cn.minsin.aop.annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

import cn.minsin.aop.MutilsAopScannerRegistrar;
import cn.minsin.aop.core.AbstractMutilsInvoke;
import cn.minsin.aop.core.DefaultLoggerInvoke;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(MutilsAopScannerRegistrar.class)
public @interface MutilsAopScanner {

	Class<? extends AbstractMutilsInvoke> referenceClass() default DefaultLoggerInvoke.class;

	Class<? extends Annotation>[] scanAnnotations() default { LoggerRecord.class };
	
	String[] scanPackages() default {};

}
