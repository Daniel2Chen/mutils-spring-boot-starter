package cn.minsin.yiketong.config;

import cn.minsin.core.init.AbstractConfig;
import lombok.Getter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: minton.zhang
 * @since: 2019/10/9 15:57
 */
@Configuration
@EnableConfigurationProperties(YiKeTongProperties.class)
public class YiKeTongAutoConfigure {
    @Getter
    private final YiKeTongProperties properties;

    YiKeTongAutoConfigure(YiKeTongProperties properties) {
        super();
        this.properties = properties;
        AbstractConfig.init(YiKeTongProperties.class, properties);
    }
}

