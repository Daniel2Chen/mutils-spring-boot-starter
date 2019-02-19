package cn.minsin.core.init.childconfig;

import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.init.core.AbstractChildrenConfig;
import cn.minsin.core.tools.StringUtil;

public class MeituanMultiConfig extends AbstractChildrenConfig {
	
	/**
	 * 配送开放平台为每个合作方分配独立的appkey，作为合作方接入认证标识。
	 */
	private String appkey;
	
	/**
	 * appkey对应的秘钥
	 */
	private String secret; 
	
	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public String getSecret() {
		return secret;
	}

	public void setSecret(String secret) {
		this.secret = secret;
	}

	@Override
	public void checkConfig() {
		slog.info("The child Config named 'MeituanMultiConfig',Required for initialization secret,appkey.");
		if(StringUtil.isBlank(secret,appkey)) {
			throw new MutilsException("The child Config named 'MeituanMultiConfig' was initialization failed. "); 
		}
	}

}
