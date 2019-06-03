package cn.minsin.aop.core;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

import cn.minsin.aop.annotation.LoggerRecord;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultLoggerInvoke extends AbstractMutilsInvoke implements AfterReturningAdvice {
	public DefaultLoggerInvoke(BeanDefinitionRegistry defaultListableBeanFactory) {
		super(defaultListableBeanFactory);
	}

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		LoggerRecord annotation = method.getAnnotation(LoggerRecord.class);
		// 指定loggerRecode 不记录日志时将跳过
		if (annotation != null && !annotation.isDo()) {
			return;
		}
		LoggerModel loggerModel = new LoggerModel(returnValue, method, args, target);
		log.info(loggerModel.toString());
	}
}
