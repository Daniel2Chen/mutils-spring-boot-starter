package org.mutils.wechat.wechatpay.core.model;

import java.math.BigDecimal;

import cn.minsin.core.annotation.NotNull;
import cn.minsin.core.tools.NumberUtil;

public class PayModel extends BaseWeChatPayModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2917095075033071637L;

	@NotNull("appid 初始化时自动填写")
	private String appid;
	
	@NotNull("商户号 初始化时自动填写")
	private String mch_id = config.getPartnerId();
	
	@NotNull("随机字符串  默认当前时间的毫秒数")
	private String nonce_str = String.valueOf(System.currentTimeMillis());
	
	@NotNull("签名类型 默认MD5")
	private String sign_type = "MD5";
	
	@NotNull("下单时的商品描述信息")
	private String body;
	
	@NotNull("接入方的生成的唯一订单号")
	private String out_trade_no;
	
	@NotNull("总金额 单位为分 必须要大于0")
	private BigDecimal total_fee;
	
	@NotNull("下单ip 默认 192.168.1.1")
	private String spbill_create_ip = "192.168.1.1";
	
	@NotNull("付款成功回调地址 初始化时自动填写")
	private String notify_url =config.getNotifyUrl();
	
	@NotNull("交易类型   可选JSAPI--JSAPI支付（或小程序支付）、NATIVE--Native支付、APP--app支付，MWEB--H5支付")
	private String trade_type;

	public String getAppid() {
		return appid;
	}

	protected void setAppid(String appid) {
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

	public BigDecimal getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(BigDecimal total_fee) {
		if(total_fee!=null) {
			total_fee = NumberUtil.yuanToFen(total_fee);
		}
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

	protected void setTrade_type(String trade_type) {
		this.trade_type = trade_type;
	}
}
