package cn.mutils.meituan.peisong.model.notify;

import cn.minsin.core.rule.AbstractModelRule;

public abstract class AbstractMeituanNotifyModel  extends AbstractModelRule{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1855257997325143293L;

	
	/**
	 * 	开放平台分配的appkey，合作方唯一标识。
	 */
	private String appkey;
	
	/**
	 * 	时间戳，格式为long，时区为GMT+8，当前距 离Epoch（1970年1月1日) 以秒计算的时间，即 unix-timestamp。
	 */
	private Long timestamp;
	
	/**
	 * 	数据签名
	 */
	private String sign;

	public String getAppkey() {
		return appkey;
	}

	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
	
	
}
