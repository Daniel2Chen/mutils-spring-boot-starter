package org.mutils.wechat.wechatpay.core.model;

import cn.minsin.core.rule.AbstractModelRule;
/**
 *      微信退款返回参数键实体类
 * @author mintonzhang
 * @date 2019年1月17日
 * @since 0.2.5
 */
public class RefundReturnModel extends AbstractModelRule {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4356953376685870476L;

	//	SUCCESS/FAIL
	private String return_code;
	//	当return_code为FAIL时返回信息为错误原因 
	private String return_msg;
	//	SUCCESS退款申请接收成功，结果通过退款查询接口查询
	private String result_code;
	//	列表详见错误码列表
	private String err_code;
	//	结果信息描述
	private String err_code_des;
	//	微信分配的公众账号ID
	private String appid;
	//	微信支付分配的商户号
	private String mch_id;
	//	随机字符串，不长于32位
	private String nonce_str;
	//	签名，详见签名算法
	private String sign;
	//	微信订单号
	private String transaction_id;
	//	商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
	private String out_trade_no;
	//	商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
	private String out_refund_no;
	//	微信退款单号
	private String refund_id;
	//	退款总金额,单位为分,可以做部分退款
	private Integer refund_fee;
	//	去掉非充值代金券退款金额后的退款金额，退款金额=申请退款金额-非充值代金券退款金额，退款金额<=申请退款金额
	private Integer settlement_refund_fee;
	//	订单总金额，单位为分，只能为整数             
	private Integer total_fee;
	//	去掉非充值代金券金额后的订单总金额，应结订单金额=订单金额-非充值代金券金额，应结订单金额<=订单金额。
	private Integer settlement_total_fee;
	//	订单金额货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY
	private String fee_type;
	//	现金支付金额，单位为分，只能为整数
	private Integer cash_fee;
	//	货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY
	private String cash_fee_type;
	//	现金退款金额，单位为分，只能为整数
	private Integer cash_refund_fee;
	//	CASH--充值代金券 
	//	NO_CASH---非充值代金券
	private Integer coupon_refund_fee;
	//	退款代金券使用数量
	private Integer coupon_refund_count;
	public String getReturn_code() {
		return return_code;
	}
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
	public String getReturn_msg() {
		return return_msg;
	}
	public void setReturn_msg(String return_msg) {
		this.return_msg = return_msg;
	}
	public String getResult_code() {
		return result_code;
	}
	public void setResult_code(String result_code) {
		this.result_code = result_code;
	}
	public String getErr_code() {
		return err_code;
	}
	public void setErr_code(String err_code) {
		this.err_code = err_code;
	}
	public String getErr_code_des() {
		return err_code_des;
	}
	public void setErr_code_des(String err_code_des) {
		this.err_code_des = err_code_des;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
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
	public String getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getOut_refund_no() {
		return out_refund_no;
	}
	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}
	public String getRefund_id() {
		return refund_id;
	}
	public void setRefund_id(String refund_id) {
		this.refund_id = refund_id;
	}
	public Integer getRefund_fee() {
		return refund_fee;
	}
	public void setRefund_fee(Integer refund_fee) {
		this.refund_fee = refund_fee;
	}
	public Integer getSettlement_refund_fee() {
		return settlement_refund_fee;
	}
	public void setSettlement_refund_fee(Integer settlement_refund_fee) {
		this.settlement_refund_fee = settlement_refund_fee;
	}
	public Integer getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(Integer total_fee) {
		this.total_fee = total_fee;
	}
	public Integer getSettlement_total_fee() {
		return settlement_total_fee;
	}
	public void setSettlement_total_fee(Integer settlement_total_fee) {
		this.settlement_total_fee = settlement_total_fee;
	}
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	public Integer getCash_fee() {
		return cash_fee;
	}
	public void setCash_fee(Integer cash_fee) {
		this.cash_fee = cash_fee;
	}
	public String getCash_fee_type() {
		return cash_fee_type;
	}
	public void setCash_fee_type(String cash_fee_type) {
		this.cash_fee_type = cash_fee_type;
	}
	public Integer getCash_refund_fee() {
		return cash_refund_fee;
	}
	public void setCash_refund_fee(Integer cash_refund_fee) {
		this.cash_refund_fee = cash_refund_fee;
	}
	public Integer getCoupon_refund_fee() {
		return coupon_refund_fee;
	}
	public void setCoupon_refund_fee(Integer coupon_refund_fee) {
		this.coupon_refund_fee = coupon_refund_fee;
	}
	public Integer getCoupon_refund_count() {
		return coupon_refund_count;
	}
	public void setCoupon_refund_count(Integer coupon_refund_count) {
		this.coupon_refund_count = coupon_refund_count;
	}
	
}
