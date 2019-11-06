package cn.minsin.gexin.push.config;

import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.init.core.AbstractChildrenConfig;
import cn.minsin.core.tools.StringUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * 	个性推送子配置项
 * @author mintonzhang
 * @date 2019年2月13日
 * @since 0.2.8
 */
@Getter
@Setter
public class GexinPushMultiProperties extends AbstractChildrenConfig {

	/**
	 * 	应用appid
	 */
	private String appid;
	/**
	 * 	应用appkey
	 */
	private String appkey;

    /**
     * 秘钥
     */
	private String masterSecret;

	@Override
	public void checkConfig() {
		slog.info("The child Config named 'GexinPushMultiConfig',Required for initialization appid, appkey,masterSecret.");
		if(StringUtil.isBlank(appid,appkey,masterSecret)) {
			throw new MutilsException("The child Config named 'GexinPushMultiConfig' was initialization failed. "); 
		}		
	}
}
