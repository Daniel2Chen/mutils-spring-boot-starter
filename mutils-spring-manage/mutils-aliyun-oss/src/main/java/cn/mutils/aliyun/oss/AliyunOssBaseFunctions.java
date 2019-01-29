package cn.mutils.aliyun.oss;

import java.net.URL;
import java.util.Date;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import cn.minsin.core.exception.MutilsErrorException;
import cn.minsin.core.init.AliyunOssConfig;
import cn.minsin.core.init.childconfig.AliyunOssMultiConfig;
import cn.minsin.core.init.core.InitConfig;
import cn.minsin.core.rule.FunctionRule;

class AliyunOssBaseFunctions extends FunctionRule {

	private static final AliyunOssConfig config = InitConfig.loadConfig(AliyunOssConfig.class);

	protected AliyunOssMultiConfig childConfig;

	protected AliyunOssBaseFunctions(AliyunOssMultiConfig config) {
		childConfig = config;
	}

	/**
	 * 初始化oss客户端
	 * 
	 * @return
	 */
	protected OSS initClient() {
		return new OSSClientBuilder().build(config.getEndpoint(), config.getAccessKeyId(), config.getAccessKeySecret());
	}

	/**
	 * Description: 判断OSS服务文件上传时文件的contentType
	 * 
	 * @param FilenameExtension 文件后缀
	 * @return String
	 */
	protected static String getContentType(String FilenameExtension) {
		if (FilenameExtension.equalsIgnoreCase(".bmp")) {
			return "image/bmp";
		}
		if (FilenameExtension.equalsIgnoreCase(".gif")) {
			return "image/gif";
		}
		if (FilenameExtension.equalsIgnoreCase(".jpeg") || FilenameExtension.equalsIgnoreCase(".jpg")
				|| FilenameExtension.equalsIgnoreCase(".png")) {
			return "image/jpeg";
		}
		if (FilenameExtension.equalsIgnoreCase(".html")) {
			return "text/html";
		}
		if (FilenameExtension.equalsIgnoreCase(".txt")) {
			return "text/plain";
		}
		if (FilenameExtension.equalsIgnoreCase(".vsd")) {
			return "application/vnd.visio";
		}
		if (FilenameExtension.equalsIgnoreCase(".pptx") || FilenameExtension.equalsIgnoreCase(".ppt")) {
			return "application/vnd.ms-powerpoint";
		}
		if (FilenameExtension.equalsIgnoreCase(".docx") || FilenameExtension.equalsIgnoreCase(".doc")) {
			return "application/msword";
		}
		if (FilenameExtension.equalsIgnoreCase(".xml")) {
			return "text/xml";
		}
		return "image/jpeg";
	}
	
	/**
	 * 初始化阿里云 OSS functions
	 * 
	 * @param prefix 配置文件中的前缀
	 * @return
	 * @throws MutilsErrorException
	 */
	public static AliyunOssBaseFunctions init(String prefix) throws MutilsErrorException {
		AliyunOssMultiConfig aliyunOssMultiConfig = config.getBucketnameAndSavedir().get(prefix);
		if (aliyunOssMultiConfig == null) {
			throw new MutilsErrorException("The config key named '" + prefix + "' not found, Please check config.");
		}
		return new AliyunOssBaseFunctions(aliyunOssMultiConfig);
	}
	

	/**
	 * 获取文件访问路径
	 * 
	 * @param key
	 * @return
	 */
	public String getUrl(String key) {
		OSS initClient = initClient();
		try {
			// 设置URL过期时间为10年 3600l* 1000*24*365*10
			Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 10);
			// 生成URL
			URL url = initClient.generatePresignedUrl(childConfig.getBucketName(), key, expiration);
			return url == null ? null : url.toString();
		} finally {
			initClient.shutdown();
		}
	}
}
