package cn.minsin.core.init;

import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.init.core.AbstractConfig;
import cn.minsin.core.tools.StringUtil;

/**
 * 	微信对移动端的配置文件
 * @author mintonzhang
 * @date 2019年2月14日
 * @since 0.1.0
 */
public class WechatAppConfig extends AbstractConfig {
	
	
	/**
	 * 	移动应用appid
	 */
	private String appid;
	
	/**
	 * 	移动应用appSecret
	 */
	private String appSecret;
	
	public String getAppid() {
		return appid;
	}
	
	/**
	 * 移动应用appid
	 */
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
			throw new MutilsException("The wechat-app config was initialization failed.");
		}
	}

}
