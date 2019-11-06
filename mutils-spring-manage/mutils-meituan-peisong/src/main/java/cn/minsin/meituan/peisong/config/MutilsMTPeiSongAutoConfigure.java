package cn.minsin.meituan.peisong.config;

import cn.minsin.core.init.core.AbstractConfig;
import lombok.Getter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: minton.zhang
 * @since: 2019/10/9 15:57
 */
@Configuration
@EnableConfigurationProperties(MutilsMTPeiSongProperties.class)
public class MutilsMTPeiSongAutoConfigure {
    @Getter
    private final MutilsMTPeiSongProperties properties;

    MutilsMTPeiSongAutoConfigure(MutilsMTPeiSongProperties properties) {
        super();
        this.properties = properties;
        AbstractConfig.init(MutilsMTPeiSongProperties.class, properties);
    }
}

