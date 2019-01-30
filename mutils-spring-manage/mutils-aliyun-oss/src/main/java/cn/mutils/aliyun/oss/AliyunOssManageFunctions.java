package cn.mutils.aliyun.oss;

import java.net.URL;
import java.util.Date;
import java.util.List;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.ObjectListing;

import cn.minsin.core.exception.MutilsErrorException;
import cn.minsin.core.init.childconfig.AliyunOssMultiConfig;
import cn.mutils.aliyun.oss.model.AliyunOssFileFilterModel;

/**
 * 阿里云OSS 文件管理
 * 官方文档  https://helpcdn.aliyun.com/document_detail/32015.html
 * @author mintonzhang
 * @date 2019年1月29日
 * @since 0.2.9
 */
public class AliyunOssManageFunctions extends AliyunOssBaseFunctions {
	protected AliyunOssManageFunctions(AliyunOssMultiConfig config) {
		super(config);
	}

	/**
	 * 查询文件是否存在 
	 * 
	 * @param fileName 文件名 调用{@link AliyunOssUploadFunctions} 获取此fileName
	 * @return
	 * @throws MutilsErrorException 
	 * @throws ClientException 
	 * @throws OSSException 
	 */
	public boolean isExists(String fileName) throws OSSException, ClientException {

		OSS initClient = initClient();
		try {
			return initClient.doesObjectExist(childConfig.getBucketName(), fileName);
		} finally {
			initClient.shutdown();
		}
	}

	/**
	 * 删除单个文件
	 * 
	 * @param fileName 文件名 调用{@link AliyunOssUploadFunctions} 获取此fileName
	 * @return
	 * @throws MutilsErrorException 
	 * @throws ClientException 
	 * @throws OSSException 
	 */
	public void deleteSingle(String fileName) throws OSSException, ClientException {
		OSS initClient = initClient();
		try {
			initClient.deleteObject(childConfig.getBucketName(), fileName);
		} finally {
			initClient.shutdown();
		}
	}

	/**
	 * 删除多个文件
	 * 
	 * @param keys 文件名 调用{@link AliyunOssUploadFunctions} 获取此fileName
	 * @return
	 * @throws MutilsErrorException 
	 * @throws ClientException 
	 * @throws OSSException 
	 */
	public void deleteMany(List<String> keys) throws OSSException, ClientException {
		OSS initClient = initClient();
		try {
			initClient.deleteObjects(new DeleteObjectsRequest(childConfig.getBucketName()).withKeys(keys).withQuiet(true));
		} finally {
			initClient.shutdown();
		}
	}

	/**
	 * 查询文件{@link ObjectListing} 以下为ObjectListing的详细解释
	 * objectSummaries	限定返回的文件元信息。	List<OSSObjectSummary> getObjectSummaries()
	 * prefix	本次查询结果的前缀。	String getPrefix()
	 * delimiter	对文件名称进行分组的一个字符。	String getDelimiter()
	 * marker	标明本次列举文件的起点。	String getMarker()
	 * maxKeys	列举文件的最大个数。	int getMaxKeys()
	 * nextMarker	下一次列举文件的起点。	String getNextMarker()
	 * isTruncated	指明列举文件是否被截断。
	 * 列举完没有截断，返回值为false。
	 * 没列举完就有截断，返回值为true。
	 * boolean isTruncated()
	 * commonPrefixes	以delimiter结尾，且有共同前缀的文件集合。	List<String> getCommonPrefixes()
	 * encodingType	指明返回结果中编码使用的类型。	String getEncodingType()
	 * 
	 * 官方文档 https://helpcdn.aliyun.com/document_detail/84841.html
	 * @param model
	 * @return
	 */
	public ObjectListing list(AliyunOssFileFilterModel model) {

		OSS initClient = initClient();
		try {
			ListObjectsRequest listObjectsRequest = model.toListObjectsRequest();
			listObjectsRequest.setBucketName(childConfig.getBucketName());
			return initClient.listObjects(listObjectsRequest);
		} finally {
			initClient.shutdown();
		}
	}

	/**
	 * 获取文件访问路径
	 * 
	 * @param fileName 文件名 调用{@link AliyunOssUploadFunctions} 获取此fileName
	 * @param timeout 有效访问时间 单位(毫秒) 最低不能少于10分钟
	 * @param method 请求方式 默认get
	 * @return
	 * @throws MutilsErrorException 
	 */
	public String getUrl(String fileName,long timeout,HttpMethod method) {
		if(timeout<600000) {
			timeout = 604800000;
		}
		if(method==null) {
			method = HttpMethod.GET;
		}
		OSS initClient = initClient();
		try {
			// 设置URL过期时间
			Date expiration = new Date(System.currentTimeMillis()+timeout);
			// 生成URL
			URL url = initClient.generatePresignedUrl(childConfig.getBucketName(), fileName, expiration,method);
			return url == null ? null : url.toString();
		} finally {
			initClient.shutdown();
		}
	}
	/**
	 * 初始化配置文件
	 * @param prefix 配置文件中的前缀
	 * @return
	 * @throws MutilsErrorException
	 */
	public static AliyunOssManageFunctions init(String prefix) throws MutilsErrorException {
		return new AliyunOssManageFunctions(loadConfig(prefix));
	}
}
