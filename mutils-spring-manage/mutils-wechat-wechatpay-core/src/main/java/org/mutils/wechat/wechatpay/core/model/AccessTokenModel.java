package org.mutils.wechat.wechatpay.core.model;

import cn.minsin.core.rule.AbstractModelRule;

/**
 * 	微信端获取access_token实体类
 * @author mintonzhang
 * @date 2019年2月14日
 * @since 0.2.0
 */
public class AccessTokenModel extends AbstractModelRule {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7065588218640296382L;
	
	private String access_token;
	
	private int expires_in;
	
	private long expires_time= System.currentTimeMillis() / 1000;
	
	private String jsapi_ticket;

	public String getAccess_token() {
		return access_token;
	}

	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}

	public long getExpires_time() {
		return expires_time;
	}

	public void setExpires_time(long expires_time) {
		this.expires_time = expires_time;
	}

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
