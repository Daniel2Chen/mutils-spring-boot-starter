package cn.minsin.aop.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.StaticMethodMatcher;

public class MutilsPointCut implements Pointcut {

	private final String[] packages;

	private final Class<? extends Annotation>[] annotations;

	public MutilsPointCut(String[] packages, Class<? extends Annotation>[] annotations) {
		super();
		this.packages = packages;
		this.annotations = annotations;
	}

	@Override
	public ClassFilter getClassFilter() {

		return new ClassFilter() {
			@Override
			public boolean matches(Class<?> clazz) {
				if (packages == null || packages.length == 0) {
					return true;
				} else {
					String name = clazz.getPackage().getName();
					for (String str : packages) {
						str = str.replace(".*", "");
						if (name.contains(name)) {
							return true;
						}
					}
				}
				return false;
			}
		};
	}

	@Override
	public MethodMatcher getMethodMatcher() {
		return new StaticMethodMatcher() {

			@Override
			public boolean matches(Method method, Class<?> targetClass) {
				return check(method);
			}
			private boolean check(Method method) {
				for (Class<? extends Annotation> anno : annotations) {
					Annotation annotation = method.getAnnotation(anno);
					if (annotation != null) {
						return true;
					}
				}
				return false;
			}
		};
	}

}
