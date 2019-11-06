package cn.minsin.wechat.miniprogram.model;

import cn.minsin.core.annotation.NotNull;
import cn.minsin.core.init.core.AbstractConfig;
import cn.minsin.wechat.miniprogram.config.MutilsWechatMiniProgramProperties;
import cn.minsin.wechat.wechatpay.core.model.PayModel;
import lombok.Getter;
import lombok.Setter;

public class MiniProgramOrderPayModel extends PayModel {


    /**
     *
     */
    private static final long serialVersionUID = -1298640499066790288L;

    public MiniProgramOrderPayModel() {
        super();
        this.setAppid(AbstractConfig.loadConfig(MutilsWechatMiniProgramProperties.class).getAppid());
        this.setTrade_type("JSAPI");
    }

    @Getter
    @Setter
    @NotNull("用户的openid")
    private String openid;
}
