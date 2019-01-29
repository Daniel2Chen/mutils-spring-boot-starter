package cn.mutils.aliyun.oss;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.PutObjectResult;

import cn.minsin.core.exception.MutilsErrorException;
import cn.minsin.core.init.childconfig.AliyunOssMultiConfig;

/**
 * 阿里云OSS 文件上传
 * 官方文档 https://helpcdn.aliyun.com/document_detail/32013.html
 * @author mintonzhang
 * @date 2019年1月29日
 * @since 0.2.9
 */
public class AliyunOssUploadFunctions extends AliyunOssBaseFunctions {


	protected AliyunOssUploadFunctions(AliyunOssMultiConfig config) {
		super(config);
	}

	/**
	 * 上传文件到Oss
	 * 
	 * @param file
	 * @return
	 * @throws MutilsErrorException
	 * @throws IOException
	 */
	public PutObjectResult uplodeToOss(MultipartFile file) throws MutilsErrorException, IOException {
		return uploadToOss(file.getOriginalFilename(), file.getInputStream());
	}

	public PutObjectResult uplodeToOss(File file) throws MutilsErrorException, FileNotFoundException {
		if (file == null || !file.exists()) {
			throw new MutilsErrorException("The file is not exists.");
		}
		return uploadToOss(file.getName(), new FileInputStream(file));
	}

	public PutObjectResult uplodeToOss(InputStream file, String fileName) throws MutilsErrorException {
		return uploadToOss(fileName, file);
	}

	/**
	 * 上传至oss
	 * 
	 * @param buckName 储存的文件空间
	 * @param fileName 文件名
	 * @param in       文件流
	 * @return {@link PutObjectResult} eTag 是阿里云中保存的文件id 需要再调用
	 * @throws MutilsErrorException
	 */
	protected PutObjectResult uploadToOss(String fileName, InputStream in) throws MutilsErrorException {
		OSS initClient = initClient();
		try {
			return initClient.putObject(childConfig.getBucketName(), childConfig.createOssSaveDir(fileName), in);
		} catch (Exception e) {
			throw new MutilsErrorException(e, "upload file failed.");
		} finally {
			initClient.shutdown();
		}
	}

}
