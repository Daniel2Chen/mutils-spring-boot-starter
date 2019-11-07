package cn.minsin.wechat.app.model;

import cn.minsin.core.init.AbstractConfig;
import cn.minsin.wechat.app.config.MutilsWechatAppProperties;
import cn.minsin.wechat.wechatpay.core.model.RefundModel;

public class AppRefundModel extends RefundModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2601251621565834984L;

	
	public AppRefundModel() {
		this.setAppid(AbstractConfig.loadConfig(MutilsWechatAppProperties.class).getAppid());
	}
}
