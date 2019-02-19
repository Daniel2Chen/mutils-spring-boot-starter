package cn.minsin.core.init;

import java.util.Map;

import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.init.childconfig.AliyunOssMultiConfig;
import cn.minsin.core.init.core.AbstractConfig;
import cn.minsin.core.tools.StringUtil;

/**
 * 阿里云对象储存配置文件
 * 
 * @author mintonzhang
 * @date 2019年2月14日
 * @since 0.1.0
 */
public class AliyunOssConfig extends AbstractConfig {

	/** 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找) */
	private String accessKeyId;

	/** 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找) */
	private String accessKeySecret;

	/** 所属地区，默认http://oss-cn-hangzhou.aliyuncs.com */
	private String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";

	/** 一个key对应 储存空间及保存目录的键值对 此key在使用时填入即可读取 */
	private Map<String, AliyunOssMultiConfig> bucketnameAndSavedir;

	public Map<String, AliyunOssMultiConfig> getBucketnameAndSavedir() {
		return bucketnameAndSavedir;
	}

	public void setBucketnameAndSavedir(Map<String, AliyunOssMultiConfig> bucketnameAndSavedir) {
		this.bucketnameAndSavedir = bucketnameAndSavedir;
	}

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

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	@Override
	protected void checkConfig() {
		slog.info("Required for initialization accessKeyId, accessKeySecret, endpoint,bucketnameAndSavedir.");
		if (StringUtil.isBlank(accessKeyId, accessKeySecret, endpoint) || bucketnameAndSavedir.isEmpty()) {
			throw new MutilsException("阿里云Oss初始化失败,请检查配置文件是否正确.");
		}
		bucketnameAndSavedir.forEach((k, v) -> {
			v.checkConfig();
		});
	}

}
