package cn.mutils.aliyun.oss.model;

import com.aliyun.oss.model.ListObjectsRequest;

import cn.minsin.core.rule.AbstractModelRule;
import cn.minsin.core.tools.StringUtil;

public class AliyunOssFileFilterModel extends AbstractModelRule {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4368484070526541535L;

	//	限定返回的文件必须以prefix作为前缀。
	private String prefix;
	//	对文件名称进行分组的一个字符。所有名称包含指定的前缀且第一次出现delimiter字符之间的文件作为一组元素（commonPrefixes）。	
	private String delimiter;
	//	列举指定marker之后的文件。	
	private String marker;
	//	限定此次列举文件的最大个数。默认值为100，最大值为1000。	
	private Integer maxKeys=100;
	//	请求响应体中文件名称采用的编码方式，目前仅支持url。	
	private String encodingType;

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public String getMarker() {
		return marker;
	}

	public void setMarker(String marker) {
		this.marker = marker;
	}

	public Integer getMaxKeys() {
		return maxKeys;
	}

	public void setMaxKeys(int maxKeys) {
		if(maxKeys>1000||maxKeys<1) {
			maxKeys = 100;
		}
		this.maxKeys = maxKeys;
	}

	public String getEncodingType() {
		return encodingType;
	}

	public void setEncodingType(String encodingType) {
		this.encodingType = encodingType;
	}
	
	public ListObjectsRequest toListObjectsRequest() {
		ListObjectsRequest req = new ListObjectsRequest();
		if(StringUtil.isNotBlank(prefix)) {
			req.withPrefix(prefix);
		}
		if(StringUtil.isNotBlank(delimiter)) {
			req.withDelimiter(delimiter);
		}
		if(StringUtil.isNotBlank(marker)) {
			req.withMarker(marker);
		}
		req.setMaxKeys(maxKeys);
		
		if(StringUtil.isNotBlank(encodingType)) {
			req.withEncodingType(encodingType);
		}
		return req;
	}
	
	
}
