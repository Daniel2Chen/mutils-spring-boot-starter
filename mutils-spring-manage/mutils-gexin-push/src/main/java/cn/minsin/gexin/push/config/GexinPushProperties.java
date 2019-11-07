package cn.minsin.gexin.push.config;

import cn.minsin.core.constant.MutilsModelConstant;
import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.init.AbstractConfig;
import cn.minsin.core.tools.StringUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * 个性推送配置文件
 *
 * @author mintonzhang
 * @date 2019年2月14日
 * @since 0.1.0
 */
@Getter
@Setter
@ConfigurationProperties(MutilsModelConstant.MUTILS_PREFIX + ".gexin.push")
public class GexinPushProperties extends AbstractConfig {

    /**
     * 多个推送信息 app-info: user: appid:xxxxx appkey:xxxxx xxxxxxxxxxxxx 初始化时
     * GexinPushFunctions.init("user").xxxxxxxxxxx 此处填写的user为配置文件中的user前缀
     */
    private Map<String, GexinPushMultiProperties> appInfo;

    /**
     * 个推服务器地址
     */
    private String url = "http://sdk.open.api.igexin.com/apiex.htm";


    @Override
    protected void checkConfig() {
        slog.info("Required for initialization appId,appKey,masterSecret,url");
        if (StringUtil.isBlank(url) || appInfo.isEmpty()) {
            throw new MutilsException("个推 初始化失败,请检查配置文件是否正确.");
        }
        appInfo.forEach((k, v) -> {
            v.checkConfig();
        });
    }

}
