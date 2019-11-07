package cn.minsin.wechat.jsapi.config;

import cn.minsin.core.init.AbstractConfig;
import lombok.Getter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: minton.zhang
 * @since: 2019/10/9 15:57
 */
@Configuration
@EnableConfigurationProperties(MutilsWechatJsapiProperties.class)
public class MutilsWechatJsapiAutoConfigure {
    @Getter
    private final MutilsWechatJsapiProperties properties;

    MutilsWechatJsapiAutoConfigure(MutilsWechatJsapiProperties properties) {
        super();
        this.properties = properties;
        AbstractConfig.init(MutilsWechatJsapiProperties.class, properties);
    }
}

