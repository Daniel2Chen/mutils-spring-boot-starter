package cn.mutils.aliyun.oss;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import cn.minsin.core.exception.MutilsErrorException;
import cn.minsin.core.init.AliyunOssConfig;
import cn.minsin.core.init.childconfig.AliyunOssMultiConfig;
import cn.minsin.core.init.core.AbstractConfig;
import cn.minsin.core.rule.AbstractFunctionRule;

class AliyunOssBaseFunctions extends AbstractFunctionRule {

	private static final AliyunOssConfig config = AbstractConfig.loadConfig(AliyunOssConfig.class);

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
	protected static  AliyunOssMultiConfig loadConfig(String prefix) throws MutilsErrorException {
		AliyunOssMultiConfig aliyunOssMultiConfig = config.getBucketnameAndSavedir().get(prefix);
		if (aliyunOssMultiConfig == null) {
			throw new MutilsErrorException("The config key named '" + prefix + "' not found, Please check config.");
		}
		return aliyunOssMultiConfig;
	}
}
