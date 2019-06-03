package cn.minsin.aop.core;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import cn.minsin.aop.annotation.MutilsAopScanner;
import cn.minsin.core.exception.MutilsException;
import lombok.extern.slf4j.Slf4j;

/**
 * 1.在实现类中可以获取到j2ee中获取的对象。
 * 
 * HttpServletRequest request = ((ServletRequestAttributes)
 * RequestContextHolder.getRequestAttributes()).getRequest();
 * HttpServletResponse response = ((ServletRequestAttributes)
 * RequestContextHolder.getRequestAttributes()).getResponse();
 * 	即通过RequestContextHolder 对象 实现类需要实现 {@link Advice} 的实现类 <br>
 * 	如下： {@link MethodBeforeAdvice } 切入方法前 
 * {@link MethodInterceptor } 环切 可以前课后
 * {@link AfterReturningAdvice } 切入方法后 
 *   继承AbstractMutilsInvoke 可获取托管至spring的bean {@code super.getBean(xxx)} 
 *   调用的方法不能标注 {@link MutilsAopScanner } 中scanAnnotations的注解，否则会造成死循环
 * 
 * @author minton。zhang
 * @date 2019年5月31日
 */
@Slf4j
public abstract class AbstractMutilsInvoke implements Advice {

	private DefaultListableBeanFactory defaultListableBeanFactory;

	public AbstractMutilsInvoke(BeanDefinitionRegistry defaultListableBeanFactory) {
		try {
			this.defaultListableBeanFactory = (DefaultListableBeanFactory) defaultListableBeanFactory;
		} catch (Exception e) {
			throw new MutilsException(e, "defaultListableBeanFactory must be extend DefaultListableBeanFactory");
		}
	}

	protected <T> T getBean(Class<T> clazz) {
		log.info("调用的方法不能标注 {@link MutilsAopScanner } 中scanAnnotations的注解，否则会造成死循环");
		return defaultListableBeanFactory.getBean(clazz);
	}

	protected Object getBean(String beanName) {
		log.info("调用的方法不能标注 {@link MutilsAopScanner } 中scanAnnotations的注解，否则会造成死循环");
		return defaultListableBeanFactory.getBean(beanName);
	}

}
