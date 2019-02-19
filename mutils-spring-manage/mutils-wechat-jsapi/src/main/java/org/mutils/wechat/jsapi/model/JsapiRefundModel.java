package org.mutils.wechat.jsapi.model;

import org.mutils.wechat.wechatpay.core.model.RefundModel;

import cn.minsin.core.init.WechatJsapiConfig;
import cn.minsin.core.init.core.AbstractConfig;

public class JsapiRefundModel extends RefundModel {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8568880096579504317L;

	public JsapiRefundModel() {
		this.setAppid(AbstractConfig.loadConfig(WechatJsapiConfig.class).getAppid());
	}
	
}
