package cn.minsin.wechat.wechatpay.core.model;

import cn.minsin.core.rule.AbstractModelRule;
import lombok.Getter;
import lombok.Setter;

/**
 * 微信端获取access_token实体类
 *
 * @author mintonzhang
 * @date 2019年2月14日
 * @since 0.2.0
 */
@Getter
@Setter
public class AccessTokenModel extends AbstractModelRule {
    /**
     *
     */
    private static final long serialVersionUID = -7065588218640296382L;

    private String access_token;

    private int expires_in;

    private long expires_time = System.currentTimeMillis() / 1000;

    private String jsapi_ticket;


    public AccessTokenModel() {
    }

    public AccessTokenModel(String access_token, int expires_in, String jsapi_ticket) {
        this.access_token = access_token;
        this.expires_in = expires_in;
        this.jsapi_ticket = jsapi_ticket;
    }

    public boolean isExpires() {
        if (access_token != null && expires_in > 0) {
            long currentTime = System.currentTimeMillis() / 1000;
            if (expires_in > (currentTime - expires_time + 30)) {
                return true;
            }
        }
        return false;
    }

    public String getJsapi_ticket() {
        return jsapi_ticket;
    }

    public void setJsapi_ticket(String jsapi_ticket) {
        this.jsapi_ticket = jsapi_ticket;
    }

}
