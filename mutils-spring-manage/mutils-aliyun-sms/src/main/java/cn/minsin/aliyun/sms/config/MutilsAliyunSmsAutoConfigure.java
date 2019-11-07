package cn.minsin.aliyun.sms.config;

import cn.minsin.core.init.AbstractConfig;
import lombok.Getter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: minton.zhang
 * @since: 2019/10/9 15:57
 */
@Configuration
@EnableConfigurationProperties(MutilsAliyunSmsProperties.class)
public class MutilsAliyunSmsAutoConfigure {
    @Getter
    private final MutilsAliyunSmsProperties properties;

    MutilsAliyunSmsAutoConfigure(MutilsAliyunSmsProperties properties) {
        super();
        this.properties = properties;
        AbstractConfig.init(MutilsAliyunSmsProperties.class, properties);
    }
}

