package cn.minsin.alipay.model;

import cn.minsin.core.annotation.NotNull;
import cn.minsin.core.rule.AbstractModelRule;
import cn.minsin.core.tools.NumberUtil;

import java.math.BigDecimal;

/**
 * 支付宝转账
 * @author mintonzhang
 * @date 2019年1月10日
 */
public class TransferModel extends AbstractModelRule {

	/**
	* 
	*/
	private static final long serialVersionUID = 5266308521996557923L;

	@NotNull("接入方生成的订单号")
	private String out_biz_no;

	@NotNull("收款方账户类型。可取值： 1、ALIPAY_USERID：支付宝账号对应的支付宝唯一用户号。以2088开头的16位纯数字组成。 2、ALIPAY_LOGONID：支付宝登录号，支持邮箱和手机号格式。")
	private String payee_type="ALIPAY_LOGONID";

	@NotNull("收款方账户。与payee_type配合使用。付款方和收款方不能是同一个账户。")
	private String payee_account;

	@NotNull("转账金额 大于0 且最多两位小数")
	private BigDecimal amount;

	@NotNull(value="转账名称",notNull=false)
	private String payer_show_name;

	@NotNull(value="提现用户的真实姓名",notNull=false)
	private String payee_real_name;

	@NotNull(value="转账备注",notNull=false)
	private String remark;

	public String getOut_biz_no() {
		return out_biz_no;
	}

	public void setOut_biz_no(String out_biz_no) {
		this.out_biz_no = out_biz_no;
	}

	public String getPayee_type() {
		return payee_type;
	}

	public void setPayee_type(String payee_type) {
		this.payee_type = payee_type;
	}

	public String getPayee_account() {
		return payee_account;
	}

	public void setPayee_account(String payee_account) {
		this.payee_account = payee_account;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		if(amount!=null) {
			amount =NumberUtil.decimalsXLength(2, amount);
		}
		this.amount = amount;
	}

	public String getPayer_show_name() {
		return payer_show_name;
	}

	public void setPayer_show_name(String payer_show_name) {
		this.payer_show_name = payer_show_name;
	}

	public String getPayee_real_name() {
		return payee_real_name;
	}

	public void setPayee_real_name(String payee_real_name) {
		this.payee_real_name = payee_real_name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
