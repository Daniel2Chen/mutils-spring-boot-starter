package cn.minsin.wechat.miniprogram.config;

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
@ConfigurationProperties(MutilsModelConstant.MUTILS_PREFIX + ".wechat.miniprogram")
public class MutilsWechatMiniProgramProperties extends AbstractConfig {


    /**
     * 小程序appid
     */
    private String appid;

    /**
     * 小程序appSecret
     */
    private String appSecret;

    @Override
    protected void checkConfig() {
        slog.info("Required for initialization appid,appSecret");
        if (StringUtil.isBlank(appid, appSecret)) {
            throw new MutilsException("小程序 初始化失败,请检查配置文件是否正确. The mini program config was initialization failed.");
        }
    }
}
