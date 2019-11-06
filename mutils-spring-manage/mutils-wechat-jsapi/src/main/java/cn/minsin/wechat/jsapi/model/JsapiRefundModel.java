package cn.minsin.wechat.jsapi.model;

import cn.minsin.core.init.core.AbstractConfig;
import cn.minsin.wechat.jsapi.config.MutilsWechatJsapiProperties;
import cn.minsin.wechat.wechatpay.core.model.RefundModel;

public class JsapiRefundModel extends RefundModel {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8568880096579504317L;

	public JsapiRefundModel() {
		this.setAppid(AbstractConfig.loadConfig(MutilsWechatJsapiProperties.class).getAppid());
	}
	
}
