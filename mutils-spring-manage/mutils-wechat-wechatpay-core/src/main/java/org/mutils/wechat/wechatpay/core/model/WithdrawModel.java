package org.mutils.wechat.wechatpay.core.model;

import java.math.BigDecimal;

import cn.minsin.core.annotation.NotNull;
import cn.minsin.core.tools.NumberUtil;

public class WithdrawModel extends BaseWeChatPayModel {


	/**
	 * 
	 */
	private static final long serialVersionUID = -780552795118313747L;

	@NotNull("申请商户号的appid或商户号绑定的appid")
	private String mch_appid;

	@NotNull("商户号 初始化时自动填写")
	private String mchid = config.getPartnerId();

	@NotNull("随机字符串 默认当前时间毫秒数")
	private String nonce_str = String.valueOf(System.currentTimeMillis());

	@NotNull("商户订单号，需保持唯一性(只能是字母或者数字，不能包含有其他字符)")
	private String partner_trade_no;

	@NotNull("商户appid下，某用户的openid")
	private String openid;

	@NotNull("NO_CHECK：不校验真实姓名  FORCE_CHECK：强校验真实姓名 默认FORCE_CHECK")
	private String check_name = "FORCE_CHECK";

	@NotNull(value="收款用户真实姓名。 如果check_name设置为FORCE_CHECK，则必填用户真实姓名",notNull=false)
	private String re_user_name;

	@NotNull("企业付款金额，单位为分 必须大于0")
	private BigDecimal amount;

	@NotNull("企业付款备注，默认提现")
	private String desc = "提现";

	@NotNull("该IP同在商户平台设置的IP白名单中的IP没有关联，该IP可传用户端或者服务端的IP。 默认192.168.1.1")
	private String spbill_create_ip = "192.168.1.1";

	public String getNonce_str() {
		return nonce_str;
	}

	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}

	public String getPartner_trade_no() {
		return partner_trade_no;
	}

	public void setPartner_trade_no(String partner_trade_no) {
		this.partner_trade_no = partner_trade_no;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getCheck_name() {
		return check_name;
	}

	public void setCheck_name(String check_name) {
		this.check_name = check_name;
	}

	public String getRe_user_name() {
		return re_user_name;
	}

	public void setRe_user_name(String re_user_name) {
		this.re_user_name = re_user_name;
	}

	public BigDecimal getAmount() {
		return amount;
	}
	
	public void setAmount(BigDecimal amount) {
		if(amount!=null) {
			amount = NumberUtil.yuanToFen(amount);
		}
		this.amount = amount;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}

	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}

	public String getMch_appid() {
		return mch_appid;
	}

	public String getMchid() {
		return mchid;
	}

	public void setMch_appid(String mch_appid) {
		this.mch_appid = mch_appid;
	}

}
