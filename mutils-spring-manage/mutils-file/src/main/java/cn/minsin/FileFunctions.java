package cn.minsin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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

import cn.minsin.core.exception.MutilsErrorException;
import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.init.FileConfig;
import cn.minsin.core.init.core.InitConfig;
import cn.minsin.core.rule.FunctionRule;
import cn.minsin.core.tools.DateUtil;
import cn.minsin.core.tools.IOUtil;
import cn.minsin.core.web.Result;

public class FileFunctions extends FunctionRule {
	
	private final static FileConfig config = InitConfig.loadConfig(FileConfig.class);


	/**
	 * path中不能出现\
	 * 
	 * @param path 2018年9月8日
	 */
	public static void checkPath(String path) {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
	}

	/**
	 * 保存单个文件
	 * 
	 * @param file 预保存文件
	 * @return
	 */
	public static String saveFile(MultipartFile file) throws MutilsErrorException {
		
		boolean local = config.isLocal();
		if (local) {
			return localSave(file);
		}
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			String[] serverList = config.getServerList();
			int nextInt = new Random().nextInt(serverList.length);
			final String remote_url = serverList[nextInt] + "/upload";// 第三方服务器请求地址
			HttpPost httpPost = new HttpPost(remote_url);
			String fileName = file.getOriginalFilename();
			MultipartEntityBuilder builder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.RFC6532);
			builder.addBinaryBody("file", file.getInputStream(), ContentType.DEFAULT_BINARY, fileName);// 文件流
			builder.addTextBody("filename", fileName);// 类似浏览器表单提交，对应input的name和value
			HttpEntity entity = builder.build();
			httpPost.setEntity(entity);
			 response = httpClient.execute(httpPost);// 执行提交
			HttpEntity responseEntity = response.getEntity();
			// 将响应内容转换为字符串
			String result = EntityUtils.toString(responseEntity, Charset.forName("UTF-8"));
			Result parseObject = JSON.parseObject(result, Result.class);
			return parseObject.getMultidata().get("url").toString();
		} catch (Exception e) {
			throw new MutilsErrorException(e, "文件保存失败. file save faild");
		} finally {
			IOUtil.close(httpClient,response);
		}
	}

	/**
	 * 批量保存文件 如果一个文件报错，不会阻断保存进程
	 * 
	 * @param file
	 * @return
	 * @throws Exception
	 */
	public static String[] saveFiles(MultipartFile[] file) throws MutilsException {
		
		String[] result = new String[file.length];
		for (int i = 0; i < result.length; i++) {
			String saveFile = null;
			try {
				saveFile = saveFile(file[i]);
			} catch (MutilsErrorException e) {
				log.info("{} save failed.error message {}", file[i].getOriginalFilename(), e);
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

	/**
	 * 
	 * @param f    源目录
	 * @param nf   目标目录
	 * @param flag 是否覆盖原文件夹 true代表把a文件夹整个复制过去，false只复制子文件夹及文件。
	 * @throws MutilsErrorException
	 */
	public static void copy(File f, File nf, boolean flag) throws MutilsErrorException {
		try {
			// 判断是否存在
			if (f.exists()) {
				// 判断是否是目录
				if (f.isDirectory()) {
					if (flag) {
						// 制定路径，以便原样输出
						nf = new File(nf + "/" + f.getName());
						// 判断文件夹是否存在，不存在就创建
						if (!nf.exists()) {
							nf.mkdirs();
						}
					}
					flag = true;
					// 获取文件夹下所有的文件及子文件夹
					File[] l = f.listFiles();
					// 判断是否为null
					if (null != l) {
						for (File ll : l) {
							// 循环递归调用
							copy(ll, nf, flag);
						}
					}
				} else {
					System.out.println("正在复制：" + f.getAbsolutePath());
					System.out.println("到：" + nf.getAbsolutePath() + "\\" + f.getName());
					// 获取输入流
					FileInputStream fis = new FileInputStream(f);
					// 获取输出流
					FileOutputStream fos = new FileOutputStream(nf + "/" + f.getName());

					int i;
					byte[] b = new byte[1024];
					// 读取文件
					while ((i = fis.read(b)) != -1) {
						// 写入文件，复制
						fos.write(b, 0, i);
					}
					IOUtil.close(fos,fis);
				}
			}
		} catch (Exception e) {
			throw new MutilsErrorException(e, "文件复制失败");
		}
	}

	protected static String localSave(MultipartFile file) throws MutilsErrorException {
		try {
			String fileName = file.getOriginalFilename();
			String gName = fileName;
			String savePath = DateUtil.date2String(new Date(), "yyyyMMdd/");
			String path = config.getSaveDisk() + savePath;
			// 定义上传路径
			checkPath(path);
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
		} catch (Exception e) {
			throw new MutilsErrorException(e, "文件保存失败. file save faild");
		}

	}

	/**
	 * 	获取文件网页访问路径
	 * 
	 * @param path    文件名和保存地址 /20190101/test.jpg
	 * @param def_img 默认图片 同path一样 填写
	 * @return
	 */
	public static String getRequestUrl(String path, String def_img) {
		
		try {
			File file = new File(config.getSaveDisk() + path);
			if(!file.exists()) {
				File def = new File(config.getSaveDisk() + def_img);
				if(!def.exists()) {
					return "";
				}
				path = def_img;
			}
		
		} catch (Exception e) {}
		return config.fullPrefix()+ path;
	}
}
