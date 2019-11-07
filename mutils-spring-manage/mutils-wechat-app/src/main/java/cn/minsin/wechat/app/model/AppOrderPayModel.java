package cn.minsin.wechat.app.model;

import cn.minsin.core.init.AbstractConfig;
import cn.minsin.wechat.app.config.MutilsWechatAppProperties;
import cn.minsin.wechat.wechatpay.core.model.PayModel;

public class AppOrderPayModel extends PayModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4389845287484100322L;
	
	
	public AppOrderPayModel() {
		this.setAppid(AbstractConfig.loadConfig(MutilsWechatAppProperties.class).getAppid());
		this.setTrade_type("APP");
	}

}
