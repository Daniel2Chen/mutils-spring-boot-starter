package cn.minsin.alipay.model;

import cn.minsin.core.rule.AbstractModelRule;

/**
 * 支付成功时 回调接口至返回的对象
 * 
 * @author mintonzhang
 * @date 2019年1月16日
 * @since 0.2.4
 */
public class NotifyModel extends AbstractModelRule {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8246523148062243090L;

	private String app_id; // 支付宝分配给开发者的应用Id
	private String notify_time; // 通知时间:yyyy-MM-dd HH:mm:ss
	private String gmt_create; // 交易创建时间:yyyy-MM-dd HH:mm:ss
	private String gmt_payment; // 交易付款时间
	private String gmt_refund; // 交易退款时间
	private String gmt_close; // 交易结束时间
	private String trade_no; // 支付宝的交易号
	private String out_trade_no; // 获取商户之前传给支付宝的订单号（商户系统的唯一订单号）
	private String out_biz_no; // 商户业务号(商户业务ID，主要是退款通知中返回退款申请的流水号)
	private String buyer_logon_id; // 买家支付宝账号
	private String seller_id; // 卖家支付宝用户号
	private String seller_email; // 卖家支付宝账号
	private String total_amount; // 订单金额:本次交易支付的订单金额，单位为人民币（元）
	private String receipt_amount; // 实收金额:商家在交易中实际收到的款项，单位为元
	private String invoice_amount; // 开票金额:用户在交易中支付的可开发票的金额
	private String buyer_pay_amount; // 付款金额:用户在交易中支付的金额
	private String trade_status; // 获取交易状态

	public String getApp_id() {
		return app_id;
	}

	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}

	public String getNotify_time() {
		return notify_time;
	}

	public void setNotify_time(String notify_time) {
		this.notify_time = notify_time;
	}

	public String getGmt_create() {
		return gmt_create;
	}

	public void setGmt_create(String gmt_create) {
		this.gmt_create = gmt_create;
	}

	public String getGmt_payment() {
		return gmt_payment;
	}

	public void setGmt_payment(String gmt_payment) {
		this.gmt_payment = gmt_payment;
	}

	public String getGmt_refund() {
		return gmt_refund;
	}

	public void setGmt_refund(String gmt_refund) {
		this.gmt_refund = gmt_refund;
	}

	public String getGmt_close() {
		return gmt_close;
	}

	public void setGmt_close(String gmt_close) {
		this.gmt_close = gmt_close;
	}

	public String getTrade_no() {
		return trade_no;
	}

	public void setTrade_no(String trade_no) {
		this.trade_no = trade_no;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getOut_biz_no() {
		return out_biz_no;
	}

	public void setOut_biz_no(String out_biz_no) {
		this.out_biz_no = out_biz_no;
	}

	public String getBuyer_logon_id() {
		return buyer_logon_id;
	}

	public void setBuyer_logon_id(String buyer_logon_id) {
		this.buyer_logon_id = buyer_logon_id;
	}

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	public String getSeller_email() {
		return seller_email;
	}

	public void setSeller_email(String seller_email) {
		this.seller_email = seller_email;
	}

	public String getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}

	public String getReceipt_amount() {
		return receipt_amount;
	}

	public void setReceipt_amount(String receipt_amount) {
		this.receipt_amount = receipt_amount;
	}

	public String getInvoice_amount() {
		return invoice_amount;
	}

	public void setInvoice_amount(String invoice_amount) {
		this.invoice_amount = invoice_amount;
	}

	public String getBuyer_pay_amount() {
		return buyer_pay_amount;
	}

	public void setBuyer_pay_amount(String buyer_pay_amount) {
		this.buyer_pay_amount = buyer_pay_amount;
	}

	public String getTrade_status() {
		return trade_status;
	}

	public void setTrade_status(String trade_status) {
		this.trade_status = trade_status;
	}

}
