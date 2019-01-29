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

	public InputStream downloadFromOss(String fileKey) throws MutilsErrorException {
		OSS initClient = initClient();
		try {
			OSSObject object = initClient.getObject(childConfig.getBucketName(), fileKey);
			return object.getObjectContent();
		} catch (Exception e) {
			throw new MutilsErrorException(e, "A file from aliyun oss  named '" + fileKey + "' download failed.");
		} finally {
			initClient.shutdown();
		}

	}

	public void downloadFromOss(String fileKey, File file) throws MutilsErrorException {
		OSS initClient = initClient();
		try {
			initClient.getObject(new GetObjectRequest(childConfig.getBucketName(), fileKey), file);
		} catch (Exception e) {
			throw new MutilsErrorException(e, "A file from aliyun oss  named '" + fileKey + "' download failed.");
		} finally {
			initClient.shutdown();
		}
	}

}
