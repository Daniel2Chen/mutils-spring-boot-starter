package cn.minsin.core.init;

import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.init.core.InitConfig;
import cn.minsin.core.tools.StringUtil;

public class DianWoDaConfig extends InitConfig {

	
	//正式服 http://api.dianwoda.com
	//测试服 http://docking-normal-qa.dwbops.com 默认
	private String url="http://docking-normal-qa.dwbops.com";
	
	private String pk;
	
	private String version = "1.0.0";
	
	private String sercret;
	
	private String format ="json";
	
	public Long getTimestamp() {
		return System.currentTimeMillis()/1000;
	}

	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}



	public String getPk() {
		return pk;
	}



	public void setPk(String pk) {
		this.pk = pk;
	}



	public String getVersion() {
		return version;
	}



	public void setVersion(String version) {
		this.version = version;
	}



	public String getSercret() {
		return sercret;
	}



	public void setSercret(String sercret) {
		this.sercret = sercret;
	}



	public String getFormat() {
		return format;
	}



	public void setFormat(String format) {
		this.format = format;
	}

	@Override
	protected void checkConfig() {
		slog.info("Required for initialization url, pk, secret.");
		if(StringUtil.isBlank(url,pk,sercret)){
			throw new MutilsException("点我达  初始化失败,请检查配置文件是否正确.");
		}
	}
}
