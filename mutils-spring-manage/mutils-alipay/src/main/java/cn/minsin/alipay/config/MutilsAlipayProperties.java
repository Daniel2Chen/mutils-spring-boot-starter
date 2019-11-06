package cn.minsin.alipay.config;

import cn.minsin.core.constant.MutilsModelConstant;
import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.init.core.AbstractConfig;
import cn.minsin.core.tools.StringUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 	支付宝配置文件
 * @author mintonzhang
 * @date 2019年2月14日
 * @since 0.1.0
 */
@Getter
@Setter
@ConfigurationProperties(MutilsModelConstant.MUTILS_PREFIX + ".alipay")
public class MutilsAlipayProperties extends AbstractConfig {

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

	@Override
	protected void checkConfig() {
		slog.info("Required for initialization appid、privateKey、publicKey、notifyUrl.");

		if (StringUtil.isBlank(appid, privateKey, publicKey, notifyUrl, serverUrl)) {
			throw new MutilsException("支付宝支付初始化失败,请检查配置文件是否正确.");
		}
	}

}
