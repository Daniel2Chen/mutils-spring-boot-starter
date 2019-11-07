package cn.minsin.wechat.jsapi.model;

import cn.minsin.core.annotation.NotNull;
import cn.minsin.core.init.AbstractConfig;
import cn.minsin.wechat.jsapi.config.MutilsWechatJsapiProperties;
import cn.minsin.wechat.wechatpay.core.model.PayModel;

public class JsapiOrderPayModel extends PayModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2340902354991037737L;

	public JsapiOrderPayModel() {
		super();
		this.setAppid(AbstractConfig.loadConfig(MutilsWechatJsapiProperties.class).getAppid());
		this.setTrade_type("JSAPI");
	}

	@NotNull("用户的openid")
	private String openid;

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}
}
