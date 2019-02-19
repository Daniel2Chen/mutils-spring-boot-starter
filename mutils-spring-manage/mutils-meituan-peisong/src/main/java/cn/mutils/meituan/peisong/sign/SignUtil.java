package cn.mutils.meituan.peisong.sign;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import cn.minsin.core.tools.StringUtil;

/**
 * 签名计算工具类
 */
public class SignUtil {

	public static String generateSign(Map<String, String> params, String secret)
			throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String encodeString = getEncodeString(params, secret);
		String sign = generateSign(encodeString);
		return sign;
	}

	private static String getEncodeString(Map<String, String> params, String secret) {
		Iterator<String> keyIter = params.keySet().iterator();
		Set<String> sortedParams = new TreeSet<>();
		while (keyIter.hasNext()) {
			sortedParams.add(keyIter.next());
		}

		StringBuilder strB = new StringBuilder(secret);

		// 排除sign和空值参数
		for (String key : sortedParams) {
			if (key.equals("sign")) {
				continue;
			}

			String value = params.get(key);

			if (StringUtil.isNotBlank(value)) {
				strB.append(key).append(value);
			}
		}
		return strB.toString();
	}

	private static String generateSign(String content) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		return Sha1(content).toLowerCase();
	}

	/**
	 * SHA1 加密
	 *
	 * @param s 待加密的字符串
	 * @return
	 */
	private static String Sha1(String s) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		byte[] btInput = s.getBytes("utf-8");
		// 获得MD5摘要算法的 MessageDigest 对象
		MessageDigest mdInst = MessageDigest.getInstance("sha-1");
		// 使用指定的字节更新摘要
		mdInst.update(btInput);
		// 获得密文
		byte[] md = mdInst.digest();
		// 把密文转换成十六进制的字符串形式
		int j = md.length;
		char str[] = new char[j * 2];
		int k = 0;
		for (int i = 0; i < j; i++) {
			byte byte0 = md[i];
			str[k++] = hexDigits[byte0 >>> 4 & 0xf];
			str[k++] = hexDigits[byte0 & 0xf];
		}
		return new String(str);
	}
}
