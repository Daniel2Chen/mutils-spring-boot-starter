package cn.minsin.core.init;

import java.util.Map;

import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.init.childconfig.GexinPushMultiConfig;
import cn.minsin.core.init.core.InitConfig;
import cn.minsin.core.tools.StringUtil;

public class GexinPushConfig extends InitConfig {
	
	
	//	多个推送配置 
	private Map<String,GexinPushMultiConfig> appInfo;
	
	//	服务器地址
	private  String url = "http://sdk.open.api.igexin.com/apiex.htm";


	public Map<String, GexinPushMultiConfig> getAppInfo() {
		return appInfo;
	}

	public void setAppInfo(Map<String, GexinPushMultiConfig> appInfo) {
		this.appInfo = appInfo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	protected void checkConfig() {
		slog.info("Required for initialization appId,appKey,masterSecret,url");
		if(StringUtil.isBlank(url)||appInfo.isEmpty()) {
			throw new MutilsException("个推 初始化失败,请检查配置文件是否正确.");
		}
	}

}
