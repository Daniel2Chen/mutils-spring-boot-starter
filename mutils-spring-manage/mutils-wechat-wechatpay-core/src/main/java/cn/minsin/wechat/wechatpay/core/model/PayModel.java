package cn.minsin.wechat.wechatpay.core.model;

import cn.minsin.core.annotation.NotNull;
import cn.minsin.core.tools.NumberUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public abstract class PayModel extends BaseWeChatPayModel {

    /**
     *
     */
    private static final long serialVersionUID = -2917095075033071637L;

    @NotNull("appid 初始化时自动填写")
    @Setter(AccessLevel.PROTECTED)
    private String appid;

    @NotNull("商户号 初始化时自动填写")
    private String mch_id = config.getPartnerId();

    @NotNull("随机字符串  默认当前时间的毫秒数")
    private String nonce_str = String.valueOf(System.currentTimeMillis());

    @NotNull("签名类型 默认MD5")
    private String sign_type = "MD5";

    @NotNull("下单时的商品描述信息")
    private String body;

    @NotNull("接入方的生成的唯一订单号")
    private String out_trade_no;

    @NotNull("总金额 单位为分 必须要大于0")
    @Setter(AccessLevel.NONE)
    private BigDecimal total_fee;

    @NotNull("下单ip 默认 192.168.1.1")
    private String spbill_create_ip = "192.168.1.1";

    @NotNull("付款成功回调地址 初始化时自动填写")
    private String notify_url = config.getNotifyUrl();

    @NotNull("交易类型   可选JSAPI--JSAPI支付（或小程序支付）、NATIVE--Native支付、APP--app支付，MWEB--H5支付")
    private String trade_type;

    @NotNull(value = "额外信息", notNull = false)
    private String attach;

    public void setTotal_fee(BigDecimal total_fee) {
        if (total_fee != null) {
            total_fee = NumberUtil.multiply(total_fee, 100, 0);
        }
        this.total_fee = total_fee;
    }

}
