package cn.minsin.excel.config;

import cn.minsin.core.init.core.AbstractConfig;
import lombok.Getter;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: minton.zhang
 * @since: 2019/10/9 15:57
 */
@Configuration
@EnableConfigurationProperties(MutilsExcelProperties.class)
public class MutilsExcelAutoConfigure {
    @Getter
    private final MutilsExcelProperties properties;

    MutilsExcelAutoConfigure(MutilsExcelProperties properties) {
        super();
        this.properties = properties;
        AbstractConfig.init(MutilsExcelProperties.class, properties);
    }
}

