package cn.minsin.core.init;

import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.init.core.AbstractConfig;
import cn.minsin.core.tools.StringUtil;

/**
 *	 移客通配置文件
 * @author mintonzhang
 * @date 2019年2月14日
 * @since 0.1.0
 */
public class YiKeTongConfig extends AbstractConfig {

	/**
	 * 	 接入方的唯一key
	 */
	private String corpKey;
	/**
	 * 	接入方秘钥
	 */
	private String corpSecret;
	/**
	 * 	服务请求地址 默认是正式服地址
	 */
	private String apiUrl = "http://api.1ketong.com:81/ykt-pool/";

	public String getCorpKey() {
		return corpKey;
	}

	public void setCorpKey(String corpKey) {
		this.corpKey = corpKey;
	}

	public String getCorpSecret() {
		return corpSecret;
	}

	public void setCorpSecret(String corpSecret) {
		this.corpSecret = corpSecret;
	}

	public String getApiUrl() {
		return apiUrl;
	}

	public void setApiUrl(String apiUrl) {
		this.apiUrl = apiUrl;
	}

	@Override
	protected void checkConfig() {
		slog.info("Required for initialization corpKey,corpSecret,apiUrl");
		if (StringUtil.isBlank(corpKey, corpSecret, apiUrl)) {
			throw new MutilsException("移客通初始化失败,请检查配置文件是否正确.");
		}

	}
}
