package cn.minsin.wechat.wechatpay.core.model;

import cn.minsin.core.rule.AbstractModelRule;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotifyModel extends AbstractModelRule {

    /**
     *
     */
    private static final long serialVersionUID = 7555460356899043084L;

    private String appid;
    private String bank_type;
    private String cash_fee;
    private String fee_type;
    private String is_subscribe;
    private String mch_id;
    private String nonce_str;
    private String openid;
    private String out_trade_no;
    private String result_code;
    private String return_code;
    private String sign;
    private String time_end;
    private String total_fee;
    private String trade_type;
    private String transaction_id;

    /**
     * 错误码
     */
    private String err_code;
    /**
     * 错误描述
     */
    private String err_code_des;

    public boolean isSuccess() {
        return "SUCCESS".equals(this.getResult_code());
    }

}
