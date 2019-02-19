package cn.minsin.core.init.core;

import cn.minsin.core.init.AlipayConfig;
import cn.minsin.core.init.AliyunOssConfig;
import cn.minsin.core.init.AliyunSmsConfig;
import cn.minsin.core.init.DianWoDaConfig;
import cn.minsin.core.init.ExcelConfig;
import cn.minsin.core.init.FileConfig;
import cn.minsin.core.init.GexinPushConfig;
import cn.minsin.core.init.KuaiDi100Config;
import cn.minsin.core.init.MeituanPeisongConfig;
import cn.minsin.core.init.UnionPayConfig;
import cn.minsin.core.init.WechatAppConfig;
import cn.minsin.core.init.WechatJsapiConfig;
import cn.minsin.core.init.WechatMiniProgramConfig;
import cn.minsin.core.init.WechatPayCoreConfig;
import cn.minsin.core.init.YiKeTongConfig;

/**
 * mutils组件列表 持续新增中.....
 * @author mintonzhang
 * @date 2019年2月13日
 * @since 0.1.0
 */
public enum MutilsFunctions {
	/**
	 *	支付宝
	 */
	MUTILS_ALIPAY("mutils-alipay", AlipayConfig.class),
	/**
	 * excel
	 */
	MUTILS_EXCEL("mutils-excel", ExcelConfig.class),
	/**
	 *	 文件上传
	 */
	MUTILS_FILE("mutils-file", FileConfig.class), 
	/**
	 * 	点我达
	 */
	MUTILS_KUAIDI100("mutils-kuaidi100",  KuaiDi100Config.class),
	/**
	 *	 移客通
	 */
	MUTILS_YIKETONG("mutils-yiketong", YiKeTongConfig.class),
	/**
	 * 	微信app支付和相关功能
	 */
	MUTILS_WECHAT_APP("mutils-wechat-app",  WechatAppConfig.class),
	/**
	 * 	微信公众号和相关功能
	 */
	MUTILS_WECHAT_JSAPI("mutils-wechat-jsapi",  WechatJsapiConfig.class),
	/**
	 *	微信小程序和相关功能
	 */
	MUTILS_WECHAT_MINIPROGRAM("mutils-wechat-miniprogram",  WechatMiniProgramConfig.class),
	/**
	 * 	微信支付核心功能
	 */
	MUTILS_WECHATPAY_CORE("mutils-wechat-wechatpay-core",  WechatPayCoreConfig.class),
	/**
	 * 	银联支付
	 */
	MUTILS_UNION_PAY("mutils-union-pay",  UnionPayConfig.class),
	/**
	 * 	个性推送
	 */
	MUTILS_GEXIN_PUSH("mutils-gexin-push",  GexinPushConfig.class),
	/**
	 *	 点我达
	 */
	MUTILS_DIANWODA("mutils-dianwoda",  DianWoDaConfig.class),
	/**
	 *	 美团配送
	 */
	MUTILS_MEITUAN_PEISONG("mutils-meituan-peisong",  MeituanPeisongConfig.class),
	/**
	 * 	阿里云-阿里大鱼
	 */
	MUTILS_ALIYUN_SMS("mutils-aliyun-sms",  AliyunSmsConfig.class),
	/**
	 * 	阿里云-oss
	 */
	MUTILS_ALIYUN_OSS("mutils-aliyun-oss",  AliyunOssConfig.class);

	private String artifactId;

	private Class<? extends AbstractConfig> clazz;

	private MutilsFunctions(String artifactId, Class<? extends AbstractConfig> clazz) {
		this.artifactId = artifactId;
		this.clazz = clazz;
	}

	public String getArtifactId() {
		return artifactId;
	}

	public void setArtifactId(String artifactId) {
		this.artifactId = artifactId;
	}

	public Class<? extends AbstractConfig> getClazz() {
		return clazz;
	}
}
