package org.mutils.wechat.wechatpay.core.model;

import java.math.BigDecimal;

import cn.minsin.core.annotation.NotNull;
import cn.minsin.core.tools.NumberUtil;

/**
 * 用户用于发起微信退款的容器
 * 
 * @author minsin
 *
 */
public class RefundModel extends BaseWeChatPayModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3579818110826754665L;
	@NotNull("appid 初始化时自动填写")
	private String appid;
	
	@NotNull("商户号 初始化时自动填写")
	private String mch_id = config.getPartnerId();
	
	@NotNull("随机字符串  默认当前时间的毫秒数")
	private String nonce_str = String.valueOf(System.currentTimeMillis());
	
	@NotNull("付款成功时微信返回的订单号")
	private String transaction_id;
	
	@NotNull("接入方生成的退款单号")
	private String out_refund_no;
	
	@NotNull("订单总金额")
	private BigDecimal total_fee;
	
	@NotNull("退款金额 不能大于总金额 且不能小于0")
	private BigDecimal refund_fee;
	
	@NotNull(value="退款结果通知url 当isSynchronizeRefund为false时自动填写",notNull=false)
	private String notify_url =config.isSynchronizeRefund()?null:config.getRefundNotifyUrl();
	
	@NotNull(value = "退款原因", notNull = false)
	private String refund_desc;

	protected void setAppid(String appid) {
		this.appid = appid;
	}

	public String getTransaction_id() {
		return transaction_id;
	}

	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}

	public String getOut_refund_no() {
		return out_refund_no;
	}

	public void setOut_refund_no(String out_refund_no) {
		this.out_refund_no = out_refund_no;
	}

	public BigDecimal getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(BigDecimal total_fee) {
		if(total_fee!=null) {
			total_fee =NumberUtil.yuanToFen(total_fee);
		}
		this.total_fee = total_fee;
	}

	public BigDecimal getRefund_fee() {
		return refund_fee;
	}

	public void setRefund_fee(BigDecimal refund_fee) {
		if(refund_fee!=null) {
			refund_fee =NumberUtil.yuanToFen(refund_fee);
		}
		this.refund_fee = refund_fee;
	}

	public String getRefund_desc() {
		return refund_desc;
	}

	public void setRefund_desc(String refund_desc) {
		this.refund_desc = refund_desc;
	}

	public String getAppid() {
		return appid;
	}

	public String getMch_id() {
		return mch_id;
	}

	public String getNonce_str() {
		return nonce_str;
	}
//
//	public String getNotify_url() {
//		return notify_url;
//	}

}