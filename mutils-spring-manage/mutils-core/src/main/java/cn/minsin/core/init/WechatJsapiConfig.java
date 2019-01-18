package cn.minsin.core.init;

import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.init.core.InitConfig;
import cn.minsin.core.tools.StringUtil;

public class WechatJsapiConfig extends InitConfig {

	// 	公众号appid
	private String appid;

	// 	公众号appSecret
	private String appSecret;

	// 	获取access_token的接口地址（GET） 限200（次/天）
	private String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";

	// 	获取jsapi_ticket_url的接口地址（GET） 限200（次/天）
	private String jsapiTicketUrl = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=ACCESS_TOKEN&type=jsapi";

	public String getAccessTokenUrl() {
		return accessTokenUrl;
	}

	public void setAccessTokenUrl(String accessTokenUrl) {
		this.accessTokenUrl = accessTokenUrl;
	}

	public String getJsapiTicketUrl() {
		return jsapiTicketUrl;
	}

	public void setJsapiTicketUrl(String jsapiTicketUrl) {
		this.jsapiTicketUrl = jsapiTicketUrl;
	}

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
		if (StringUtil.isBlank(appid, appSecret)) {
			throw new MutilsException("The wechat-jsapi config was initialization failed.");
		}

	}
}
