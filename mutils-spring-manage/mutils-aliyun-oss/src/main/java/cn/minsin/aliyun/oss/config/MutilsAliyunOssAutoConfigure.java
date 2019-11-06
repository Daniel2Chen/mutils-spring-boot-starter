package cn.minsin.aliyun.oss.config;

import cn.minsin.core.init.core.AbstractConfig;
import lombok.Getter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: minton.zhang
 * @since: 2019/10/9 15:57
 */
@Configuration
@EnableConfigurationProperties(MutilsAliyunOssProperties.class)
public class MutilsAliyunOssAutoConfigure {
    @Getter
    private final MutilsAliyunOssProperties properties;

    MutilsAliyunOssAutoConfigure(MutilsAliyunOssProperties properties) {
        super();
        this.properties = properties;
        AbstractConfig.init(MutilsAliyunOssProperties.class, properties);
    }
}

