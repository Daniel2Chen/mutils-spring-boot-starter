package cn.minsin.core.init.childconfig;

import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.init.core.AbstractChildrenConfig;
import cn.minsin.core.tools.StringUtil;

/**
 * 	个性推送子配置项
 * @author mintonzhang
 * @date 2019年2月13日
 * @since 0.2.8
 */
public class GexinPushMultiConfig extends AbstractChildrenConfig {

	/**
	 * 	应用appid
	 */
	private String appid;
	/**
	 * 	应用appkey
	 */
	private String appkey;

	private String masterSecret;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public String getMasterSecret() {
		return masterSecret;
	}

	public void setMasterSecret(String masterSecret) {
		this.masterSecret = masterSecret;
	}

	@Override
	public void checkConfig() {
		slog.info("The child Config named 'GexinPushMultiConfig',Required for initialization appid, appkey,masterSecret.");
		if(StringUtil.isBlank(appid,appkey,masterSecret)) {
			throw new MutilsException("The child Config named 'GexinPushMultiConfig' was initialization failed. "); 
		}		
	}
}
