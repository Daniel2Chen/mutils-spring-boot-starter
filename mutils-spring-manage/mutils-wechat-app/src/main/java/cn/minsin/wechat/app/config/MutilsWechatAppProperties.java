package cn.minsin.wechat.app.config;

import cn.minsin.core.constant.MutilsModelConstant;
import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.init.AbstractConfig;
import cn.minsin.core.tools.StringUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * 快递100配置文件
 *
 * @author mintonzhang
 * @date 2019年2月14日
 * @since 0.1.0
 */
@Getter
@Setter
@ConfigurationProperties(MutilsModelConstant.MUTILS_PREFIX + ".wechat.app")
public class MutilsWechatAppProperties extends AbstractConfig {

    /**
     * 	移动应用appid
     */
    private String appid;

    /**
     * 	移动应用appSecret
     */
    private String appSecret;

    @Override
    protected void checkConfig() {
        slog.info("Required for initialization appid,appSecret");
        if(StringUtil.isBlank(appid,appSecret)) {
            throw new MutilsException("The wechat-app config was initialization failed.");
        }
    }
}
