package cn.minsin.core.init;

import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.init.core.AbstractConfig;
import cn.minsin.core.tools.StringUtil;

/**
 * 	支付宝配置文件
 * @author mintonzhang
 * @date 2019年2月14日
 * @since 0.1.0
 */
public class AlipayConfig extends AbstractConfig {

	/** 1.支付宝的APPID 需要在官方申请*/
	private String appid;

	/** 2.私钥 pkcs8格式的*/
	private String privateKey;

	/** 3.支付宝公钥 */
	private String publicKey;

	/** 4.异步通知页面路径 不能写localhost或127.0.0.1等内网地址，必须要填写外网能够访问到的地址	*/
	private String notifyUrl;

	/** 5.请求网关地址 默认https:openapi.alipay.com/gateway.do */
	private String serverUrl = "https://openapi.alipay.com/gateway.do";

	/** 6.编码 默认UTF-8	*/
	private String charset = "UTF-8";

	/** 7.返回格式 	默认json */
	private String format = "json";

	/** 8.加密类型(推荐使用RSA2)	 默认RSA2 */
	private String signType = "RSA2";

	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getServerUrl() {
		return serverUrl;
	}

	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	@Override
	protected void checkConfig() {
		slog.info("Required for initialization appid、privateKey、publicKey、notifyUrl.");

		if (StringUtil.isBlank(appid, privateKey, publicKey, notifyUrl, serverUrl)) {
			throw new MutilsException("支付宝支付初始化失败,请检查配置文件是否正确.");
		}
	}

}
