package cn.minsin.wechat.wechatpay.core.model;

import cn.minsin.core.annotation.NotNull;
import cn.minsin.core.exception.MutilsErrorException;
import cn.minsin.core.init.core.AbstractConfig;
import cn.minsin.core.rule.AbstractModelRule;
import cn.minsin.core.tools.ModelUtil;
import cn.minsin.core.tools.StringUtil;
import cn.minsin.wechat.wechatpay.core.config.WechatPayCoreProperties;
import cn.minsin.wechat.wechatpay.core.util.SignUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.util.SortedMap;


public abstract class BaseWeChatPayModel extends AbstractModelRule {

    protected final static WechatPayCoreProperties config = AbstractConfig.loadConfig(WechatPayCoreProperties.class);

    /**
     *
     */
    private static final long serialVersionUID = -6916140873435262221L;

    @NotNull(value = "签名  自动生成无须填写", notNull = false)
    @Getter
    @Setter(AccessLevel.PROTECTED)
    private String sign;

    /**
     * 生成xml
     *
     * @return
     * @throws MutilsErrorException
     */
    public String toXml(String partnerKey) {
        SortedMap<String, String> treeMap = ModelUtil.toTreeMap(this);
        String sign = SignUtil.createSign(treeMap, partnerKey);
        this.setSign(sign);
        StringBuilder sb = new StringBuilder("<xml>");
        for (Field field : ModelUtil.getAllFields(this)) {
            try {
                if (ModelUtil.verificationField(field)) {
                    continue;
                }
                field.setAccessible(true);
                Object object = field.get(this);
                if (!StringUtil.isBlank(object)) {
                    sb.append("<").append(field.getName()).append(">");
                    sb.append(object).append("</").append(field.getName()).append(">");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sb.append("</xml>").toString();
    }
}
