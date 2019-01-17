package cn.minsin.core.init;

import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.init.core.InitConfig;
import cn.minsin.core.tools.StringUtil;

public class AliyunSmsConfig extends InitConfig {
	
     //	此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
     private  String accessKeyId;
     
     //	此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
     private  String accessKeySecret;
     
     //	产品名称:云通信短信API产品,开发者无需替换
     private String product ="Dysmsapi";
     
     //	产品域名,开发者无需替换
     private String domain = "dysmsapi.aliyuncs.com";
     
	public String getAccessKeyId() {
		return accessKeyId;
	}

	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}

	public String getAccessKeySecret() {
		return accessKeySecret;
	}

	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}
	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}
	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	@Override
	protected void checkConfig() {
		slog.info("Required for initialization accessKeyId, accessKeySecret, product,domain.");
		if(StringUtil.isBlank(accessKeyId, accessKeySecret, product,domain)){
			throw new MutilsException("阿里大鱼  初始化失败,请检查配置文件是否正确.");
		}
	}

}
