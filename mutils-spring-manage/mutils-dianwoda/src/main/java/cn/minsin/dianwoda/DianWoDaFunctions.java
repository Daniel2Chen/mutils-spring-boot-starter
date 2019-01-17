package cn.minsin.dianwoda;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.minsin.core.exception.MutilsErrorException;
import cn.minsin.core.init.DianWoDaConfig;
import cn.minsin.core.init.core.InitConfig;
import cn.minsin.core.rule.FunctionRule;
import cn.minsin.core.tools.MapUtil;
import cn.minsin.dianwoda.model.OrderModel;
import cn.minsin.dianwoda.util.SignUtil;

public class DianWoDaFunctions extends FunctionRule {

	private final static DianWoDaConfig config = InitConfig.loadConfig(DianWoDaConfig.class);

	/**
	 * 派发订单 /api/v3/order-send.json
	 * 
	 * @return
	 * @throws Exception
	 */
	public static JSONObject order_send(OrderModel ot) throws MutilsErrorException {
		return doSend("/api/v3/order-send.json", MapUtil.toMap(ot));
	}

	/**
	 * 模拟发送http请求
	 *
	 * @param api            业务api接口
	 * @param businessParams 业务参数
	 * @return 响应结果
	 */
	protected static JSONObject doSend(String url, Map<String, Object> businessParams) throws MutilsErrorException {

		/* 生成签名 */
		String sign = SignUtil.sign(businessParams, config.getSercret());

		businessParams.put("pk", config.getPk());
		businessParams.put("v", config.getVersion());
		businessParams.put("format", config.getFormat());
		businessParams.put("sig", sign);
		businessParams.put("timestamp", config.getTimestamp());
		try {
			HttpPost post = new HttpPost(config.getUrl() + url);
			List<NameValuePair> list = new LinkedList<>();
			businessParams.keySet().forEach(k -> {
				list.add(new BasicNameValuePair(k, businessParams.get(k).toString()));
			});
			UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(list, "UTF-8");
			post.setEntity(uefEntity);

			CloseableHttpClient build = HttpClientBuilder.create().build();
			log.info("Request infomation is {}", JSONObject.toJSONString(list));
			CloseableHttpResponse response = build.execute(post);
			HttpEntity entity = response.getEntity();
			String string = EntityUtils.toString(entity);
			response.close();
			build.close();
			log.info("Request infomation is {}", string);
			return JSON.parseObject(string);
		} catch (Exception e) {
			throw new MutilsErrorException(e, "点我达请求下单失败");
		}
	}

}
