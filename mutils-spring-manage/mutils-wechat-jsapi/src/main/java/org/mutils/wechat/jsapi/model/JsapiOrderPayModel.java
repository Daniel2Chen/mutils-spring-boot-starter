package org.mutils.wechat.jsapi.model;

import org.mutils.wechat.wechatpay.core.model.PayModel;

import cn.minsin.core.annotation.NotNull;
import cn.minsin.core.init.WechatJsapiConfig;
import cn.minsin.core.init.core.AbstractConfig;

public class JsapiOrderPayModel extends PayModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2340902354991037737L;

	public JsapiOrderPayModel() {
		super();
		this.setAppid(AbstractConfig.loadConfig(WechatJsapiConfig.class).getAppid());
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
