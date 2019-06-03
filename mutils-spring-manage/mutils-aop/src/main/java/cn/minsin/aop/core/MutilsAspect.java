package cn.minsin.aop.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;

import org.aopalliance.aop.Advice;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.core.annotation.AnnotationAttributes;

import cn.minsin.core.exception.MutilsException;

@Aspect
public class MutilsAspect implements PointcutAdvisor {

	private AbstractMutilsInvoke invoke;

	private Class<? extends Annotation>[] annotations;

	private String[] packages;

	public MutilsAspect(AnnotationAttributes metadata, BeanDefinitionRegistry reg) {
		this.init(metadata, reg);
	}

	@Override
	public Advice getAdvice() {
		return invoke;
	}

	@Override
	public boolean isPerInstance() {
		return false;
	}

	@Override
	public Pointcut getPointcut() {
		return new MutilsPointCut(packages, annotations);
	}

	@SuppressWarnings("unchecked")
	private void init(AnnotationAttributes metadata, BeanDefinitionRegistry reg) {
		Class<? extends Object> clazz = metadata.getClass("referenceClass");
		try {
			Constructor<? extends Object> constructor = clazz.getConstructor(BeanDefinitionRegistry.class);
			AbstractMutilsInvoke newInstance = (AbstractMutilsInvoke) constructor.newInstance(reg);
			invoke = (AbstractMutilsInvoke) newInstance;
			annotations = (Class<? extends Annotation>[]) metadata.getClassArray("scanAnnotations");
			packages = metadata.getStringArray("scanPackages");
		} catch (Exception e) {
			throw new MutilsException(e);
		}
	}
}
