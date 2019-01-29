package cn.mutils.aliyun.oss;

import java.util.List;

import com.aliyun.oss.OSS;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.ObjectListing;

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
	 * @param key
	 * @return
	 */
	public boolean isExists(String key) {

		OSS initClient = initClient();
		try {
			return initClient.doesObjectExist(childConfig.getBucketName(), key);
		} finally {
			initClient.shutdown();
		}
	}

	/**
	 * 删除单个文件
	 * 
	 * @param key
	 * @return
	 */
	public boolean deleteSingle(String key) {
		OSS initClient = initClient();
		try {
			initClient.deleteObject(childConfig.getBucketName(), key);
			return true;
		} finally {
			initClient.shutdown();
		}
	}

	/**
	 * 删除多个文件
	 * 
	 * @param keys
	 * @return
	 */
	public boolean deleteMany(List<String> keys) {
		OSS initClient = initClient();
		try {
			initClient.deleteObjects(
					new DeleteObjectsRequest(childConfig.getBucketName()).withKeys(keys).withQuiet(true));
			return true;
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
}
