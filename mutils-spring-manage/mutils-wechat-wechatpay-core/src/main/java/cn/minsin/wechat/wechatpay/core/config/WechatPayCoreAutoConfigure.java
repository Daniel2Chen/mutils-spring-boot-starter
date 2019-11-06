package cn.minsin.wechat.wechatpay.core.config;

import cn.minsin.core.constant.MutilsModelConstant;
import cn.minsin.core.init.core.AbstractConfig;
import lombok.Getter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author: minton.zhang
 * @since: 2019/10/9 15:57
 */
@Configuration
@EnableConfigurationProperties(WechatPayCoreProperties.class)
@ConditionalOnProperty(MutilsModelConstant.MUTILS_PREFIX + ".wechat.pay.enable")
public class WechatPayCoreAutoConfigure {
    @Getter
    private final WechatPayCoreProperties properties;

    WechatPayCoreAutoConfigure(WechatPayCoreProperties properties) {
        super();
        this.properties = properties;
        AbstractConfig.init(WechatPayCoreProperties.class, properties);
    }
}

