package cn.minsin.alipay.model;

import java.math.BigDecimal;

import cn.minsin.core.annotation.NotNull;
import cn.minsin.core.rule.AbstractModelRule;
import cn.minsin.core.tools.NumberUtil;

/**
 * 支付宝支付所需参数
 * @author mintonzhang
 * @date 2019年1月10日
 */
public class PayModel  extends AbstractModelRule{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8285357081519576784L;
	
	@NotNull("反馈给用户的付款原因")
	private String subject;
	
	@NotNull("接入方生成的订单号")
	private String out_trade_no;
	
	@NotNull("付款金额 必须大于0且最多两位小数")
	private BigDecimal total_amount;
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public BigDecimal getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(BigDecimal total_amount) {
		if(total_amount!=null) {
			total_amount =NumberUtil.keepLenthDecimals(2, total_amount);
		}
		this.total_amount = total_amount;
	}
}
