package cn.minsin.kuaidi100.config;

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
@ConfigurationProperties(MutilsModelConstant.MUTILS_PREFIX + ".kuaidi100")
public class MutilsKuaiDi100Properties extends AbstractConfig {

    /**
     * 用户id
     */
    private String customer;

    /**
     * 秘钥
     */
    private String key;

    /**
     * 请求地址
     */
    private String url;

    @Override
    protected void checkConfig() {
        slog.info("Required for initialization customer,key,url");
        if (StringUtil.isBlank(customer, key, url)) {
            throw new MutilsException("快递100 初始化失败,请检查配置文件是否正确.");
        }
    }
}
