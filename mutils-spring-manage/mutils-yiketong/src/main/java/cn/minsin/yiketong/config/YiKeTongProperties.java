package cn.minsin.yiketong.config;

import cn.minsin.core.constant.MutilsModelConstant;
import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.init.core.AbstractConfig;
import cn.minsin.core.init.core.ConfigEnvironment;
import cn.minsin.core.tools.StringUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 移客通配置文件
 *
 * @author mintonzhang
 * @date 2019年2月14日
 * @since 0.1.0
 */
@Getter
@Setter
@ConfigurationProperties(MutilsModelConstant.MUTILS_PREFIX + ".yiketong")
public class YiKeTongProperties extends AbstractConfig {

    /**
     * 接入方的唯一key
     */
    private String corpKey;
    /**
     * 接入方秘钥
     */
    private String corpSecret;
    /**
     * 服务请求地址 默认是正式服地址
     */
    private String apiUrl;
    /**
     * 环境 默认测试环境
     */
    private ConfigEnvironment environment = ConfigEnvironment.TEST;


    public String getApiUrl() {
        if (StringUtil.isBlank(apiUrl)) {
            return ConfigEnvironment.TEST == environment ? "http://api.1ketong.com:81/ykt-pool/" : "http://api.1ketong.com/ykt-pool/";
        }
        return apiUrl;
    }


    @Override
    protected void checkConfig() {
        slog.info("Required for initialization corpKey,corpSecret,apiUrl");
        if (StringUtil.isBlank(corpKey, corpSecret, apiUrl)) {
            throw new MutilsException("移客通初始化失败,请检查配置文件是否正确.");
        }
    }
}
