package cn.minsin.wechat.wechatpay.core.model;

import cn.minsin.core.annotation.NotNull;
import cn.minsin.core.tools.NumberUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
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
    @Setter(AccessLevel.NONE)
	private BigDecimal amount;

	@NotNull("企业付款备注，默认提现")
	private String desc = "提现";

	@NotNull("该IP同在商户平台设置的IP白名单中的IP没有关联，该IP可传用户端或者服务端的IP。 默认192.168.1.1")
	private String spbill_create_ip = "192.168.1.1";


	public void setAmount(BigDecimal amount) {
		if(amount!=null) {
			amount = NumberUtil.multiply(amount,100,0);
		}
		this.amount = amount;
	}

}
