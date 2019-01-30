package cn.minsin.core.init.core;

import cn.minsin.core.init.*;

public enum MutilsFunctions {
	MUTILS_ALIPAY("mutils-alipay", AlipayConfig.class),
	MUTILS_EXCEL("mutils-excel", ExcelConfig.class),
	MUTILS_FILE("mutils-file", FileConfig.class), 
	MUTILS_KUAIDI100("mutils-kuaidi100",  KuaiDi100Config.class),
	MUTILS_YIKETONG("mutils-yiketong", YiKeTongConfig.class),
	MUTILS_WECHAT_APP("mutils-wechat-app",  WechatAppConfig.class),
	MUTILS_WECHAT_JSAPI("mutils-wechat-jsapi",  WechatJsapiConfig.class),
	MUTILS_WECHAT_MINIPROGRAM("mutils-wechat-miniprogram",  WechatMiniProgramConfig.class),
	MUTILS_WECHATPAY_CORE("mutils-wechat-wechatpay-core",  WechatPayCoreConfig.class),
	MUTILS_UNION_PAY("mutils-union-pay",  UnionPayConfig.class),
	MUTILS_GEXIN_PUSH("mutils-gexin-push",  GexinPushConfig.class),
	MUTILS_DIANWODA("mutils-dianwoda",  DianWoDaConfig.class),
	MUTILS_ALIYUN_SMS("mutils-aliyun-sms",  AliyunSmsConfig.class),
	MUTILS_ALIYUN_OSS("mutils-aliyun-oss",  AliyunOssConfig.class);

	private String artifactId;

	private Class<? extends InitConfig> clazz;

	private MutilsFunctions(String artifactId, Class<? extends InitConfig> clazz) {
		this.artifactId = artifactId;
		this.clazz = clazz;
	}

	public String getArtifactId() {
		return artifactId;
	}



	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}



	public Class<? extends InitConfig> getClazz() {
		return clazz;
	}
}
