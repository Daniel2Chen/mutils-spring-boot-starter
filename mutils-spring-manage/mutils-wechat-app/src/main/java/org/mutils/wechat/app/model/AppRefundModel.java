package org.mutils.wechat.app.model;

import org.mutils.wechat.wechatpay.core.model.RefundModel;

import cn.minsin.core.init.WechatAppConfig;
import cn.minsin.core.init.core.AbstractConfig;

public class AppRefundModel extends RefundModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2601251621565834984L;

	
	public AppRefundModel() {
		this.setAppid(AbstractConfig.loadConfig(WechatAppConfig.class).getAppid());
	}
}
