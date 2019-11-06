package cn.minsin.wechat.wechatpay.core.model;

import cn.minsin.core.rule.AbstractModelRule;
import lombok.Getter;
import lombok.Setter;

/**
 *      微信退款返回参数键实体类
 * @author mintonzhang
 * @date 2019年1月17日
 * @since 0.2.5
 */
@Getter
@Setter
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

}
