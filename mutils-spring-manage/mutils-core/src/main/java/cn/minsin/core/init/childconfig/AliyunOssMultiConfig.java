package cn.minsin.core.init.childconfig;

public class AliyunOssMultiConfig {
	// 默认储存空间
	private String bucketName;

	// 默认保存目录
	private String saveDir;

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getSaveDir() {
		return saveDir;
	}

	public void setSaveDir(String saveDir) {
		this.saveDir = saveDir;
	}

	public String createOssSaveDir(String fileName) {
		if (saveDir == null) {
			return fileName;
		}
		return saveDir + "/" + fileName;
	}
}