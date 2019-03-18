package cn.minsin.core.init;

import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.init.childconfig.MeituanMultiConfig;
import cn.minsin.core.init.core.AbstractConfig;
import cn.minsin.core.init.core.ConfigEnvironment;
import cn.minsin.core.tools.StringUtil;

/**
 * 美团配送相关配置文件
 * 
 * @author mintonzhang
 * @date 2019年2月18日
 * @since 0.3.4
 */
public class MeituanPeisongConfig extends AbstractConfig {

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
	private MeituanMultiConfig testConfig = new MeituanMultiConfig();

	/**
	 * 正式环境
	 */
	private MeituanMultiConfig formalConfig = new MeituanMultiConfig();

	public ConfigEnvironment getEnvironment() {
		return environment;
	}

	public void setEnvironment(ConfigEnvironment environment) {
		this.environment = environment;
	}

	public MeituanMultiConfig getTestConfig() {
		return testConfig;
	}

	public void setTestConfig(MeituanMultiConfig testConfig) {
		this.testConfig = testConfig;
	}

	public MeituanMultiConfig getFormalConfig() {
		return formalConfig;
	}

	public void setFormalConfig(MeituanMultiConfig formalConfig) {
		this.formalConfig = formalConfig;
	}

	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public MeituanMultiConfig getConfig() {
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
