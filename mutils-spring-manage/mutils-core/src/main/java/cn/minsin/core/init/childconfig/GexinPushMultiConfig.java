package cn.minsin.core.init.childconfig;

public class GexinPushMultiConfig {

	// 	应用appid
	private String appid;
	// 	应用appkey
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
}
