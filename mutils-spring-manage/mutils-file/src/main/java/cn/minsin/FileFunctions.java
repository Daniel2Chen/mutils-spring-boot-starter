package cn.minsin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Random;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;

import cn.minsin.core.init.FileConfig;
import cn.minsin.core.init.core.AbstractConfig;
import cn.minsin.core.rule.AbstractFunctionRule;
import cn.minsin.core.tools.DateUtil;
import cn.minsin.core.tools.FileUtil;
import cn.minsin.core.tools.IOUtil;
import cn.minsin.core.web.Result;

public class FileFunctions extends AbstractFunctionRule {

	private final static FileConfig config = AbstractConfig.loadConfig(FileConfig.class);

	/**
	 * 保存单个文件
	 * 
	 * @param file 预保存文件
	 * @return
	 * @throws IOException
	 */
	public static String saveFile(MultipartFile file) throws IOException {

		boolean local = config.isLocal();
		if (local) {
			return localSave(file);
		}
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			String[] serverList = config.getServerList();
			int nextInt = new Random().nextInt(serverList.length);
			// 第三方服务器请求地址
			final String remote_url = serverList[nextInt] + "/upload";
			HttpPost httpPost = new HttpPost(remote_url);
			String fileName = file.getOriginalFilename();
			MultipartEntityBuilder builder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.RFC6532);
			builder.addBinaryBody("file", file.getInputStream(), ContentType.DEFAULT_BINARY, fileName);// 文件流
			builder.addTextBody("filename", fileName);
			HttpEntity entity = builder.build();
			httpPost.setEntity(entity);
			response = httpClient.execute(httpPost);
			HttpEntity responseEntity = response.getEntity();
			//	将响应内容转换为字符串
			String result = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
			Result parseObject = JSON.parseObject(result, Result.class);
			return parseObject.getMultidata().get("url").toString();
		} finally {
			IOUtil.close(httpClient, response);
		}
	}

	/**
	 * 批量保存文件 如果一个文件报错，不会阻断保存进程
	 * 
	 * @param file
	 * @return
	 * @throws IOException 
	 * @throws Exception
	 */
	public static String[] saveFiles(MultipartFile[] file) throws IOException {

		String[] result = new String[file.length];
		for (int i = 0; i < result.length; i++) {
			String saveFile = null;
			try {
				saveFile = saveFile(file[i]);
			} catch (IOException e) {
				LOGGER.info("{} save failed.error message {}", file[i].getOriginalFilename(), e);
			}
			if (saveFile != null) {
				result[i] = saveFile;
			}
		}
		return result;
	}

	/**
	 * 判断是否超过最大文件数量
	 *
	 * @param files
	 * @param length     最大length
	 * @param isRequired 是否必填
	 * @return 2018年7月30日
	 */
	public static boolean maxLength(MultipartFile[] files, int length, boolean isRequired) {
		if (files != null) {
			return files.length > length ? true : false;
		}
		return isRequired;
	}

	/**
	 * 判断是否大于限制的size
	 *
	 * @param files 文件
	 * @param mSize M做单位
	 * @return 大于限制大小返回true 反之false
	 */
	public static boolean checkSize(MultipartFile[] files, int mSize) {
		Long limitSize = (mSize * 1024L * 1024L);
		Long sumSize = 0L;
		if (files != null && files.length > 0) {
			for (MultipartFile multipartFile : files) {
				sumSize += multipartFile.getSize();// size的单位为kb
			}
		}
		return sumSize > limitSize ? true : false;
	}

	protected static String localSave(MultipartFile file) throws IOException {
		String fileName = file.getOriginalFilename();
		String gName = fileName;
		String savePath = DateUtil.date2String(new Date(), "yyyyMMdd/");
		String path = config.getSaveDisk() + savePath;
		// 定义上传路径
		FileUtil.checkPath(path);
		int count = 0;
		while (true) {
			String fileUrl = path + gName;
			boolean exists = new File(fileUrl).exists();
			if (exists) {
				int index = fileName.lastIndexOf(".");
				String extension = "";
				if (index > 0) {
					extension = fileName.substring(index, fileName.length());
				}
				count++;
				gName = fileName.replace(extension, "") + "-副本(" + count + ")" + extension;
				continue;
			}
			// 写入文件
			OutputStream out = new FileOutputStream(fileUrl);
			out.write(file.getBytes());
			out.flush();
			out.close();
			return savePath + gName;
		}

	}
}
