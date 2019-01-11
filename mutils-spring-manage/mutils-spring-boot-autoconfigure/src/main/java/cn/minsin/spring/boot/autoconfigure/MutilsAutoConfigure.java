package cn.minsin.spring.boot.autoconfigure;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import cn.minsin.core.init.core.InitConfig;

@Configuration
@EnableConfigurationProperties(MutilsProperties.class)
public class MutilsAutoConfigure {
	
	@Configuration
	static class MutilsParamConfiguration {
		private final MutilsProperties properties;

		MutilsParamConfiguration(MutilsProperties properties) {
			super();
			this.properties = properties;
			InitConfig.init(properties.getFunctions());
		}
		public MutilsProperties getProperties() {
			return properties;
		}
	}
}
