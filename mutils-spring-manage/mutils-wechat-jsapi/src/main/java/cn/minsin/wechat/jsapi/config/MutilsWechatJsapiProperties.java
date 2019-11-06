package cn.minsin.wechat.jsapi.config;

import cn.minsin.core.constant.MutilsModelConstant;
import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.init.core.AbstractConfig;
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
@ConfigurationProperties(MutilsModelConstant.MUTILS_PREFIX + ".wechat.jsapi")
public class MutilsWechatJsapiProperties extends AbstractConfig {


    /**
     * 		公众号appid
     */
    private String appid;

    /**
     * 	公众号appSecret
     */
    private String appSecret;

    /**
     * 	获取access_token的接口地址（GET） 限200（次/天）
     */
    private String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

    /**
     * 	获取jsapi_ticket_url的接口地址（GET） 限200（次/天）
     */
    private String jsapiTicketUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";


    @Override
    protected void checkConfig() {
        slog.info("Required for initialization appid,appSecret");
        if (StringUtil.isBlank(appid, appSecret)) {
            throw new MutilsException("The wechat-jsapi config was initialization failed.");
        }
    }
}
