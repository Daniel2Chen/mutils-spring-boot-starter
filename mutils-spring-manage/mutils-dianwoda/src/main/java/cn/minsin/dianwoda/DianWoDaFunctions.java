package cn.minsin.dianwoda;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.minsin.core.init.DianWoDaConfig;
import cn.minsin.core.init.core.AbstractConfig;
import cn.minsin.core.rule.AbstractFunctionRule;
import cn.minsin.core.tools.HttpClientUtil;
import cn.minsin.core.tools.IOUtil;
import cn.minsin.core.tools.MapUtil;
import cn.minsin.dianwoda.model.OrderModel;
import cn.minsin.dianwoda.util.SignUtil;

public class DianWoDaFunctions extends AbstractFunctionRule {

	private final static DianWoDaConfig config = AbstractConfig.loadConfig(DianWoDaConfig.class);

	/**
	 * 派发订单 /api/v3/order-send.json
	 * 
	 * @return
	 * @throws IOException
	 * @throws ClientProtocolException
	 * @throws Exception
	 */
	public static JSONObject order_send(OrderModel ot) throws ClientProtocolException, IOException {
		return doSend("/api/v3/order-send.json", MapUtil.toMap(ot));
	}

	/**
	 * 模拟发送http请求
	 * 
	 * @param api            业务api接口
	 * @param businessParams 业务参数
	 * @return 响应结果
	 * @throws IOException
	 * @throws ClientProtocolException
	 */
	protected static JSONObject doSend(String url, Map<String, Object> businessParams)
			throws ClientProtocolException, IOException {
		CloseableHttpClient build = HttpClientUtil.getInstance();
		CloseableHttpResponse response = null;
		try {
			/* 生成签名 */
			String sign = SignUtil.sign(businessParams, config.getSercret());

			businessParams.put("pk", config.getPk());
			businessParams.put("v", config.getVersion());
			businessParams.put("format", config.getFormat());
			businessParams.put("sig", sign);
			businessParams.put("timestamp", config.getTimestamp());

			HttpPost post = new HttpPost(config.getUrl() + url);
			List<NameValuePair> list = new LinkedList<>();
			businessParams.keySet().forEach(k -> {
				list.add(new BasicNameValuePair(k, businessParams.get(k).toString()));
			});
			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(list, "UTF-8");
			post.setEntity(uefEntity);

			LOGGER.info("Request infomation is {}", JSONObject.toJSONString(list));
			response = build.execute(post);
			HttpEntity entity = response.getEntity();
			String string = EntityUtils.toString(entity);
			LOGGER.info("Request infomation is {}", string);
			return JSON.parseObject(string);
		} finally {
			IOUtil.close(build, response);
		}
	}

}
