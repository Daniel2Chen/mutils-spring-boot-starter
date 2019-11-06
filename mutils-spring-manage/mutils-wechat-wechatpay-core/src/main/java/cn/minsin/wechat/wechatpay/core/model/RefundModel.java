package cn.minsin.wechat.wechatpay.core.model;

import cn.minsin.core.annotation.NotNull;
import cn.minsin.core.tools.NumberUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 用户用于发起微信退款的容器
 * 
 * @author minsin
 *
 */
@Getter
@Setter
public class RefundModel extends BaseWeChatPayModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3579818110826754665L;

	@NotNull("appid 初始化时自动填写")
    @Setter(AccessLevel.PROTECTED)
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
    @Setter(AccessLevel.NONE)
	private BigDecimal total_fee;
	
	@NotNull("退款金额 不能大于总金额 且不能小于0")
    @Setter(AccessLevel.NONE)
	private BigDecimal refund_fee;
	
	@NotNull(value="退款结果通知url 当isSynchronizeRefund为false时自动填写",notNull=false)
	private String notify_url =config.isSynchronizeRefund()?null:config.getRefundNotifyUrl();
	
	@NotNull(value = "退款原因", notNull = false)
	private String refund_desc;


	public void setTotal_fee(BigDecimal total_fee) {
		if(total_fee!=null) {
			total_fee =NumberUtil.multiply(total_fee,100,0);
		}
		this.total_fee = total_fee;
	}


	public void setRefund_fee(BigDecimal refund_fee) {
		if(refund_fee!=null) {
			refund_fee =NumberUtil.multiply(refund_fee,100,0);
		}
		this.refund_fee = refund_fee;
	}
}