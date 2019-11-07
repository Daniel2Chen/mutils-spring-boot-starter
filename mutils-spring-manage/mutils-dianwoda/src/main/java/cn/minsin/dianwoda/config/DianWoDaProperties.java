package cn.minsin.dianwoda.config;

import cn.minsin.core.constant.MutilsModelConstant;
import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.init.AbstractConfig;
import cn.minsin.core.tools.StringUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 	点我达配置文件
 * @author mintonzhang
 * @date 2019年2月14日
 * @since 0.1.0
 */
@Getter
@Setter
@ConfigurationProperties(prefix = MutilsModelConstant.MUTILS_PREFIX + ".dianwoda")
public class DianWoDaProperties extends AbstractConfig {

	
	/**
	 * 	正式服 http://api.dianwoda.com
	 * 	测试服 http://docking-normal-qa.dwbops.com 默认
	 */
	private String url="http://docking-normal-qa.dwbops.com";
	
	private String pk;
	
	private String version = "1.0.0";
	
	private String sercret;
	
	private String format ="json";
	
	public long getTimestamp() {
		return System.currentTimeMillis()/1000;
	}


	@Override
	protected void checkConfig() {
		slog.info("Required for initialization url, pk, secret.");
		if(StringUtil.isBlank(url,pk,sercret)){
			throw new MutilsException("点我达  初始化失败,请检查配置文件是否正确.");
		}
	}
}
