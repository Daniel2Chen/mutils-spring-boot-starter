package cn.minsin.core.init;

import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.init.core.AbstractConfig;
import cn.minsin.core.tools.StringUtil;

/**
 * 	微信对小程序的配置文件
 * @author mintonzhang
 * @date 2019年2月14日
 * @since 0.1.0
 */
public class WechatMiniProgramConfig extends AbstractConfig {
	
	
	/**
	 * 	小程序appid
	 */
	private String appid;
	
	/**
	 * 	小程序appSecret
	 */
	private String appSecret;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	@Override
	protected void checkConfig() {
		slog.info("Required for initialization appid,appSecret");

		if(StringUtil.isBlank(appid,appSecret)) {
			throw new MutilsException("小程序 初始化失败,请检查配置文件是否正确. The mini program config was initialization failed.");
		}
	}

}
