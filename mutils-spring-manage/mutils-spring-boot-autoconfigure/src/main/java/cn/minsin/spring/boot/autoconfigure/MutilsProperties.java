package cn.minsin.spring.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

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
import cn.minsin.core.init.core.MutilsFunctions;

@ConfigurationProperties(prefix = MutilsProperties.MUTILS_PREFIX)
public class MutilsProperties {
	
	public static final String MUTILS_PREFIX = "mutils";

	/**
	 * 选择需要初始化的功能,必填
	 */
	private MutilsFunctions[] functions;
	/**
	 * 支付宝初始化参数
	 */
	private AlipayConfig alipay = new AlipayConfig();

	/**
	 * excel参数配置
	 */
	private ExcelConfig excel = new ExcelConfig();
	
	/**
	 * 	文件上传指定参数
	 */
	private FileConfig file = new FileConfig();
	
	/**
	 * 快递100配置文件
	 */
	private KuaiDi100Config kuaidi100 = new KuaiDi100Config();
	

	/**
	 * 移客通配置文件
	 */
	private YiKeTongConfig yiketong = new YiKeTongConfig();
	
	/**
	 * 点我达配置文件
	 */
	private DianWoDaConfig dianwoda = new DianWoDaConfig();
	
	/**
	 * 微信支付相关配置  注意：微信支付必须要填写此项
	 */
	private  WechatPayCoreConfig wechatPayCore = new WechatPayCoreConfig();
	
	/**
	 * 微信-小程序相关配置
	 */
	private WechatMiniProgramConfig wechatMiniProgram = new WechatMiniProgramConfig();
	
	/**
	 * 微信-app相关配置
	 */
	private WechatAppConfig wechatApp = new WechatAppConfig();
	
	/**
	 * 微信-jsapi的相关配置文件
	 */
	private WechatJsapiConfig wechatJsapi = new WechatJsapiConfig();
	
	/**
	 * 	银联支付
	 */
	private UnionPayConfig unionPay = new UnionPayConfig();
	/**
	 * 	个推推送
	 */
	private GexinPushConfig gexinPush = new GexinPushConfig();
	/**
	 * 	阿里大鱼短信
	 */
	private AliyunSmsConfig aliyunSms = new AliyunSmsConfig();
	/**
	 * 	阿里云Oss储存
	 */
	private AliyunOssConfig aliyunOss = new AliyunOssConfig();
	/**
	 * 	美团配送
	 */
	private MeituanPeisongConfig meituanPeisong= new MeituanPeisongConfig();
	
	/**
	 * mutils功能组件系列相关介绍：Ctrl+t 找到对应的config文件。比如alipay 那么对应的config就是alipayConfig
	 * 在属性中，如果那项属性有默认值，一般是不用填写的。具体请看其属性注释
	 */
	private String readMe;
	
	
	public MeituanPeisongConfig getMeituanPeisong() {
		return meituanPeisong;
	}

	public void setMeituanPeisong(MeituanPeisongConfig meituanPeisong) {
		this.meituanPeisong = meituanPeisong;
	}

	public AliyunOssConfig getAliyunOss() {
		return aliyunOss;
	}

	public void setAliyunOss(AliyunOssConfig aliyunOss) {
		this.aliyunOss = aliyunOss;
	}

	public AliyunSmsConfig getAliyunSms() {
		return aliyunSms;
	}

	public void setAliyunSms(AliyunSmsConfig aliyunSms) {
		this.aliyunSms = aliyunSms;
	}

	public UnionPayConfig getUnionPay() {
		return unionPay;
	}

	public void setUnionPay(UnionPayConfig unionPay) {
		this.unionPay = unionPay;
	}

	public GexinPushConfig getGexinPush() {
		return gexinPush;
	}

	public void setGexinPush(GexinPushConfig gexinPush) {
		this.gexinPush = gexinPush;
	}

	public String getReadMe() {
		return readMe;
	}

	public void setReadMe(String readMe) {
		this.readMe = readMe;
	}

	public WechatAppConfig getWechatApp() {
		return wechatApp;
	}

	public void setWechatApp(WechatAppConfig wechatApp) {
		this.wechatApp = wechatApp;
	}

	public WechatJsapiConfig getWechatJsapi() {
		return wechatJsapi;
	}

	public void setWechatJsapi(WechatJsapiConfig wechatJsapi) {
		this.wechatJsapi = wechatJsapi;
	}

	public MutilsFunctions[] getFunctions() {
		return functions;
	}

	public void setFunctions(MutilsFunctions[] functions) {
		this.functions = functions;
	}
	
	public AlipayConfig getAlipay() {
		return alipay;
	}

	public void setAlipay(AlipayConfig alipay) {
		this.alipay = alipay;
	}

	public ExcelConfig getExcel() {
		return excel;
	}

	public void setExcel(ExcelConfig excel) {
		this.excel = excel;
	}

	public FileConfig getFile() {
		return file;
	}

	public void setFile(FileConfig file) {
		this.file = file;
	}

	public KuaiDi100Config getKuaidi100() {
		return kuaidi100;
	}

	public void setKuaidi100(KuaiDi100Config kuaidi100) {
		this.kuaidi100 = kuaidi100;
	}

	public YiKeTongConfig getYiketong() {
		return yiketong;
	}

	public void setYiketong(YiKeTongConfig yiketong) {
		this.yiketong = yiketong;
	}

	public DianWoDaConfig getDianwoda() {
		return dianwoda;
	}

	public void setDianwoda(DianWoDaConfig dianwoda) {
		this.dianwoda = dianwoda;
	}

	public WechatPayCoreConfig getWechatPayCore() {
		return wechatPayCore;
	}

	public void setWechatPayCore(WechatPayCoreConfig wechatPayCore) {
		this.wechatPayCore = wechatPayCore;
	}

	public WechatMiniProgramConfig getWechatMiniProgram() {
		return wechatMiniProgram;
	}

	public void setWechatMiniProgram(WechatMiniProgramConfig wechatMiniProgram) {
		this.wechatMiniProgram = wechatMiniProgram;
	}
}
