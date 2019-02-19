package org.mutils.wechat.miniprogram.model;

import org.mutils.wechat.wechatpay.core.model.RefundModel;

import cn.minsin.core.init.WechatMiniProgramConfig;
import cn.minsin.core.init.core.AbstractConfig;

public class MiniProgramRefundModel extends RefundModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8450162902614994429L;

	
	public MiniProgramRefundModel() {
		this.setAppid(AbstractConfig.loadConfig(WechatMiniProgramConfig.class).getAppid());
	}
}
