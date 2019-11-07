package cn.minsin.meituan.peisong.config;

import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.init.AbstractChildrenConfig;
import cn.minsin.core.tools.StringUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MutilsMTMultiProperties extends AbstractChildrenConfig {
	
	/**
	 * 配送开放平台为每个合作方分配独立的appkey，作为合作方接入认证标识。
	 */
	private String appkey;
	
	/**
	 * appkey对应的秘钥
	 */
	private String secret; 


	@Override
	public void checkConfig() {
		slog.info("The child Config named 'MeituanMultiConfig',Required for initialization secret,appkey.");
		if(StringUtil.isBlank(secret,appkey)) {
			throw new MutilsException("The child Config named 'MeituanMultiConfig' was initialization failed. "); 
		}
	}

}
