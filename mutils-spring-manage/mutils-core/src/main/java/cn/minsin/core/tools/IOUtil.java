package cn.minsin.core.tools;

import cn.minsin.core.exception.MutilsErrorException;
import com.alibaba.fastjson.util.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URLConnection;

/**
 * 	文件流相关工具类  可参考{@link IOUtils}
 * @author mintonzhang
 * @date 2019年1月15日
 */
public class IOUtil extends IOUtils {

	/**
	 * 关闭流
	 * 
	 * @param ios
	 */
	public static void close(Closeable... ios) {
		if (ios != null) {
			for (Closeable x : ios) {
				IOUtils.close(x);
			}
		}
	}
	
	/**
	 * 关闭流
	 * 
	 * @param ios
	 */
	public static void close(AutoCloseable... ios) {
		if (ios != null) {
			for (AutoCloseable x : ios) {
				if(x!=null) {
					try {
						x.close();
					} catch (Exception e) {
					}
				}
			}
		}
	}

    public static void close(final URLConnection conn) {
        if (conn instanceof HttpURLConnection) {
            ((HttpURLConnection) conn).disconnect();
        }
    }

	/**
	 * 	将文件流转成字节缓存在内存中，可以让流多次使用。使用{@link ByteArrayInputStream} 创建新的输入流
	 * 
	 * @param in
	 * @return
	 * @throws MutilsErrorException
	 */
	public static byte[] copyInputStream(InputStream in) throws MutilsErrorException {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len;
			while ((len = in.read(buffer)) > -1) {
				baos.write(buffer, 0, len);
			}
			baos.flush();
			return baos.toByteArray();
		} catch (Exception e) {
			throw new MutilsErrorException(e, "Copy the inputStream failed.");
		} finally {
			close(in);
		}
	}
}
