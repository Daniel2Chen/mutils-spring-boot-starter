package cn.minsin.wechat.miniprogram.model;

import cn.minsin.core.init.core.AbstractConfig;
import cn.minsin.wechat.miniprogram.config.MutilsWechatMiniProgramProperties;
import cn.minsin.wechat.wechatpay.core.model.RefundModel;

public class MiniProgramRefundModel extends RefundModel {

    /**
     *
     */
    private static final long serialVersionUID = 8450162902614994429L;


    public MiniProgramRefundModel() {
        this.setAppid(AbstractConfig.loadConfig(MutilsWechatMiniProgramProperties.class).getAppid());
    }
}
