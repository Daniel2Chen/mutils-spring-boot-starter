package cn.minsin.meituan.peisong.model.send;

import cn.minsin.core.annotation.NotNull;
import cn.minsin.core.exception.MutilsErrorException;
import cn.minsin.core.init.AbstractConfig;
import cn.minsin.core.rule.AbstractModelRule;
import cn.minsin.core.tools.ModelUtil;
import cn.minsin.meituan.peisong.config.MutilsMTMultiProperties;
import cn.minsin.meituan.peisong.config.MutilsMTPeiSongProperties;
import cn.minsin.meituan.peisong.sign.SignUtil;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * 美团抽象订单查询基类
 * 
 * @author mintonzhang
 * @date 2019年2月18日
 * @since 0.2.
 */
public abstract class AbstractMeituanSendModel extends AbstractModelRule {
	/**
	 * 
	 */
	private static final long serialVersionUID = -892913836531408286L;

	private static final MutilsMTPeiSongProperties loadConfig;

	static{
        loadConfig = AbstractConfig.loadConfig(MutilsMTPeiSongProperties.class);
    }


	private static MutilsMTMultiProperties childConfig;

	public AbstractMeituanSendModel() {
		childConfig = loadConfig.getConfig();
		this.setAppkey(childConfig.getAppkey());
		this.setVersion(loadConfig.getVersion());
	}

	@NotNull("配送开放平台为每个合作方分配独立的appkey，作为合作方接入认证标识。\n"
			+ "* 每个appkey会绑定一个secret，用于计算签名。请妥善保管secret，避免泄密。如果secret意外泄露，可要求重新生成。")
	private String appkey;

	@NotNull("时间戳，格式为long，时区为GMT+8，即合作方调用接口时距离Epoch（1970年1月1日) 以秒计算的时间（unix-timestamp）。* 开放平台允许合作方请求最大时间误差为10分钟（配送开放平台接到请求的时间 - 合作方调用接口的时间 < 10分钟）。")
	private Long timestamp = System.currentTimeMillis() / 1000;

	@NotNull("API协议版本，可选值：1.0。")
	private String version = "1.0";

	public String getAppkey() {
		return appkey;
	}

	protected void setAppkey(String appkey) {
		this.appkey = appkey;
	}

	public long getTimestamp() {
		return timestamp;
	}

	protected void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getVersion() {
		return version;
	}

	protected void setVersion(String version) {
		this.version = version;
	}

	public String getServerUrl() {
		return loadConfig.getServerUrl();
	}

	/**
	 * 生成map
	 * 
	 * @return
	 * @throws MutilsErrorException
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public Map<String, String> toMap()
			throws MutilsErrorException, NoSuchAlgorithmException, UnsupportedEncodingException {
		Map<String, String> treeMap = ModelUtil.toTreeMap(this);
		String sign = SignUtil.generateSign(treeMap, childConfig.getSecret());
		treeMap.put("sign", sign);
		return treeMap;
	}
}
