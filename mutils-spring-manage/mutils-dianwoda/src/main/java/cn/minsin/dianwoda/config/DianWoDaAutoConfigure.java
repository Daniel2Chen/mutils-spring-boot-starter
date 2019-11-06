package cn.minsin.dianwoda.config;

import cn.minsin.core.init.core.AbstractConfig;
import lombok.Getter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: minton.zhang
 * @since: 2019/10/9 15:57
 */
@Configuration
@EnableConfigurationProperties(DianWoDaProperties.class)
public class DianWoDaAutoConfigure {
    @Getter
    private final DianWoDaProperties properties;

    DianWoDaAutoConfigure(DianWoDaProperties properties) {
        super();
        this.properties = properties;
        AbstractConfig.init(DianWoDaProperties.class, properties);
    }
}

