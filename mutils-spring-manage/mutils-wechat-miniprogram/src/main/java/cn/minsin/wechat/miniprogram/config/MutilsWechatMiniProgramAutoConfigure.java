package cn.minsin.wechat.miniprogram.config;

import cn.minsin.core.init.core.AbstractConfig;
import lombok.Getter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: minton.zhang
 * @since: 2019/10/9 15:57
 */
@Configuration
@EnableConfigurationProperties(MutilsWechatMiniProgramProperties.class)
public class MutilsWechatMiniProgramAutoConfigure {
    @Getter
    private final MutilsWechatMiniProgramProperties properties;

    MutilsWechatMiniProgramAutoConfigure(MutilsWechatMiniProgramProperties properties) {
        super();
        this.properties = properties;
        AbstractConfig.init(MutilsWechatMiniProgramProperties.class, properties);
    }
}

