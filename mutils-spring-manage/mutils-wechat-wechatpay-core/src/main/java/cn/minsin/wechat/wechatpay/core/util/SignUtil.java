package cn.minsin.wechat.wechatpay.core.util;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;


public class SignUtil {


	/**
	 * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
	 * @param packageParams 参与签名的数据
	 * @param partnerKey 商户秘钥
	 * @return
	 */
	public static String createSign(SortedMap<String, String> packageParams,String partnerKey) {
		StringBuffer sb = new StringBuffer();
		Set<Entry<String, String>> es = packageParams.entrySet();
		Iterator<Entry<String, String>> it = es.iterator();
		while (it.hasNext()) {
			 Entry<String, String> entry = it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		System.out.println("MD5 String:" + sb + "key=" + partnerKey);
		String sign = MD5Util.MD5Encode(sb.append("key=").append(partnerKey).toString(),"UTF-8").toUpperCase();
		System.out.println("packge签名:" + sign);
		return sign;

	}
}
