/**
 * 
 */
package cn.minsin.kuaidi100;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import cn.minsin.core.exception.MutilsErrorException;
import cn.minsin.core.init.KuaiDi100Config;
import cn.minsin.core.init.core.InitConfig;
import cn.minsin.core.rule.FunctionRule;
import cn.minsin.core.tools.HttpClientUtil;
import cn.minsin.core.tools.IOUtil;
import cn.minsin.kuaidi100.util.MD5Util;

/**
 * 快递100物流查询
 * 
 * @author mintonzhang 2018年7月19日
 */
public class KuaiDi100Functions extends FunctionRule {

	private final static KuaiDi100Config config = InitConfig.loadConfig(KuaiDi100Config.class);

	/**
	 * 查询物流单号
	 * 
	 * @param logisticsCode   物流公司code
	 * @param logisticsNumber 物流单号
	 * @return 2018年7月20日
	 */
	public static String getLogistics(String logisticsCode, String logisticsNumber) throws MutilsErrorException {
		CloseableHttpClient httpclient = null;
		CloseableHttpResponse response = null;
		try {
			String param = "{\"com\":\"" + logisticsNumber + "\",\"num\":\"" + logisticsCode + "\"}";
			String sign = MD5Util.encode(param + config.getKey() + config.getCustomer());
			List<NameValuePair> params = new ArrayList<>();
			params.add(new BasicNameValuePair("param", param));
			params.add(new BasicNameValuePair("sign", sign));
			params.add(new BasicNameValuePair("customer", config.getCustomer()));
			httpclient = HttpClientUtil.getInstance();
			HttpPost post = HttpClientUtil.getPostMethod(config.getUrl());
			post.setEntity(new UrlEncodedFormEntity(params, "utf-8"));
			 response = httpclient.execute(post);
			String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
			System.out.println("物流json是:" + jsonStr);
			return jsonStr;
		} catch (Exception e) {
			throw new MutilsErrorException(e, "快递100查询物流失败");
		}finally {
			IOUtil.close(response,httpclient);
		}
	}
}
