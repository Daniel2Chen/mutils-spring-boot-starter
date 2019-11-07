package cn.minsin.aliyun.sms.config;

import cn.minsin.core.constant.MutilsModelConstant;
import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.init.AbstractConfig;
import cn.minsin.core.tools.StringUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * excel配置文件
 *
 * @author mintonzhang
 * @date 2019年2月14日
 * @since 0.1.0
 */
@Getter
@Setter
@ConfigurationProperties(prefix = MutilsModelConstant.MUTILS_PREFIX + ".aliyun.sms")
public class MutilsAliyunSmsProperties extends AbstractConfig {

    /**
     * 	此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
     */
    private String accessKeyId;

    /**
     *	 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
     */
    private String accessKeySecret;

    /**
     * 	产品名称:云通信短信API产品,开发者无需替换
     */
    private String product = "Dysmsapi";

    /**
     * 	产品域名,开发者无需替换 默认 dysmsapi.aliyuncs.com
     */
    private String domain = "dysmsapi.aliyuncs.com";

    /**
     * 	连接超时时间 默认 1000
     */
    private long connectTimeout = 1000;

    /**
     *	 读取超时时间  默认 1000
     */
    private long readTimeout = 1000;


    @Override
    protected void checkConfig() {
        slog.info("Required for initialization accessKeyId, accessKeySecret, product,domain.");
        if (StringUtil.isBlank(accessKeyId, accessKeySecret, product, domain)) {
            throw new MutilsException("阿里大鱼  初始化失败,请检查配置文件是否正确.");
        }
    }


}
