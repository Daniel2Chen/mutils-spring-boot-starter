package cn.mutils.aliyun.oss;

import java.io.File;
import java.io.InputStream;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;

import cn.minsin.core.exception.MutilsErrorException;
import cn.minsin.core.init.childconfig.AliyunOssMultiConfig;

/**
 * 阿里云OSS 文件下载
 * 官方文档 https://helpcdn.aliyun.com/document_detail/32014.html
 * @author mintonzhang
 * @date 2019年1月29日
 * @since 0.2.9
 */
public class AliyunOssDownloadFunctions extends AliyunOssBaseFunctions {

	protected AliyunOssDownloadFunctions(AliyunOssMultiConfig config) {
		super(config);
	}

	/**
	 * 下载文件 
	 * @param fileName 文件名 调用{@link AliyunOssUploadFunctions} 获取此fileName
	 * @return 文件流
	 */
	public InputStream downloadFromOss(String fileName){
		OSS initClient = initClient();
		try {
			OSSObject object = initClient.getObject(childConfig.getBucketName(), fileName);
			return object.getObjectContent();
		} finally {
			initClient.shutdown();
		}

	}
	/**
	 * 下载文件 
	 * @param fileName 文件名 调用{@link AliyunOssUploadFunctions} 获取此fileName
	 * @param file 存放oss下载出的文件
	 */
	public void downloadFromOss(String fileName, File file){
		OSS initClient = initClient();
		try {
			initClient.getObject(new GetObjectRequest(childConfig.getBucketName(), fileName), file);
		} finally {
			initClient.shutdown();
		}
	}
	/**
	 * 初始化配置文件
	 * @param prefix
	 * @return
	 * @throws MutilsErrorException
	 */
	public static AliyunOssDownloadFunctions init(String prefix) throws MutilsErrorException {
		return new AliyunOssDownloadFunctions(loadConfig(prefix));
	}

}
