package org.mutils.wechat.wechatpay.core.model;

import cn.minsin.core.init.WechatPayCoreConfig;

public class PayModel extends BaseWeChatPayModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2917095075033071637L;

	// 小程序 app 公众号 需要填写对应的appid
	private String appid;

	private String mch_id = WechatPayCoreConfig.wechatPayConfig.getPartnerId();

	private String nonce_str = String.valueOf(System.currentTimeMillis());

	private String sign;

	private String sign_type = "MD5";

	private String body;

	private String out_trade_no;

	private int total_fee;

	private String spbill_create_ip = "192.168.1.1";

	private String notify_url;

	private String trade_type;

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public int getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(int total_fee) {
		this.total_fee = total_fee;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getNotify_url() {
		return notify_url;
	}

	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}

	public String getTrade_type() {
		return trade_type;
	}

	public void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
}