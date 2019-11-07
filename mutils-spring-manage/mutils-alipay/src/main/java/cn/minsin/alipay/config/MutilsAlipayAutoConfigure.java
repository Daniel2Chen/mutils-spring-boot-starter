package cn.minsin.alipay.config;

import cn.minsin.core.init.AbstractConfig;
import lombok.Getter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: minton.zhang
 * @since: 2019/10/9 15:57
 */
@Configuration
@EnableConfigurationProperties(MutilsAlipayProperties.class)
public class MutilsAlipayAutoConfigure {
    @Getter
    private final MutilsAlipayProperties properties;

    MutilsAlipayAutoConfigure(MutilsAlipayProperties properties) {
        super();
        this.properties = properties;
        AbstractConfig.init(MutilsAlipayProperties.class, properties);
    }
}

