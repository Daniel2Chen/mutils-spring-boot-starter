package cn.minsin.core.init;

import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.init.core.InitConfig;
import cn.minsin.core.tools.StringUtil;

public class GexinPushConfig extends InitConfig {
	
	//	应用appid
	private  String appid;
	//	应用appkey
	private  String appkey;
	
	private  String masterSecret;
	
	//	服务器地址
	private  String url = "http://sdk.open.api.igexin.com/apiex.htm";

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	protected void checkConfig() {
		slog.info("Required for initialization appId,appKey,masterSecret,url");
		if(StringUtil.isBlank(appid,appkey,masterSecret,url)) {
			throw new MutilsException("个推 初始化失败,请检查配置文件是否正确.");
		}
	}

}
