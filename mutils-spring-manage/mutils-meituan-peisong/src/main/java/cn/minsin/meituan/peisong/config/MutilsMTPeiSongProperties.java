package cn.minsin.meituan.peisong.config;

import cn.minsin.core.constant.MutilsModelConstant;
import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.init.AbstractConfig;
import cn.minsin.core.init.ConfigEnvironment;
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
@ConfigurationProperties(prefix = MutilsModelConstant.MUTILS_PREFIX + ".meituan.peisong")
public class MutilsMTPeiSongProperties extends AbstractConfig {

    /**
     * 服务器地址
     */
    private String serverUrl = "https://peisongopen.meituan.com/api";

    /**
     * 版本信息
     */
    private String version = "1.0";

    /**
     * 需要使用的环境 默认测试环境 所选环境的config必须配置
     */
    private ConfigEnvironment environment = ConfigEnvironment.TEST;

    /**
     * 测试环境
     */
    private MutilsMTMultiProperties testConfig = new MutilsMTMultiProperties();

    /**
     * 正式环境
     */
    private MutilsMTMultiProperties formalConfig = new MutilsMTMultiProperties();



    public MutilsMTMultiProperties getConfig() {
        return environment == ConfigEnvironment.TEST ? testConfig : formalConfig;
    }

    @Override
    protected void checkConfig() {
        slog.info("Required for initialization version,serverUrl,environment,testConfig,formalConfig.");
        if (StringUtil.isBlank(version, serverUrl, environment, testConfig, formalConfig)) {
            throw new MutilsException("The mutils-meituan-peisong config was initialization failed.");
        }
        getConfig().checkConfig();
    }


}
