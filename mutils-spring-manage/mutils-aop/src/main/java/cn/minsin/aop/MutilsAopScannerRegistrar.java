package cn.minsin.aop;

import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;

import cn.minsin.aop.annotation.MutilsAopScanner;
import cn.minsin.aop.core.MutilsAspect;

public class MutilsAopScannerRegistrar  implements ImportBeanDefinitionRegistrar  {


	@Override
	public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
		AnnotationAttributes metadata = AnnotationAttributes
				.fromMap(importingClassMetadata.getAnnotationAttributes(MutilsAopScanner.class.getName()));
		AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder
				.genericBeanDefinition(MutilsAspect.class)
				.addConstructorArgValue(metadata)
				.addConstructorArgValue(registry)
				.getBeanDefinition();
		registry.registerBeanDefinition(beanDefinition.getBeanClassName(), beanDefinition);
	}
}
