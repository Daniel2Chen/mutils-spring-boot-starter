package org.mutils.wechat.jsapi;

import java.io.IOException;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.jdom.JDOMException;
import org.mutils.wechat.jsapi.model.JsapiOrderPayModel;
import org.mutils.wechat.jsapi.model.JsapiRefundModel;
import org.mutils.wechat.wechatpay.core.WeChatPayFunctions;
import org.mutils.wechat.wechatpay.core.model.AccessTokenModel;
import org.mutils.wechat.wechatpay.core.model.RefundReturnModel;
import org.mutils.wechat.wechatpay.core.util.Sha1Util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.minsin.core.exception.MutilsErrorException;
import cn.minsin.core.init.WechatJsapiConfig;
import cn.minsin.core.init.core.AbstractConfig;
import cn.minsin.core.tools.HttpClientUtil;
import cn.minsin.core.tools.IOUtil;

/**
 * jsapi相关功能
 * 
 * @author mintonzhang
 * @date 2019年1月10日
 */
public class WechatJsapiFunctions extends WeChatPayFunctions {
	
	private static final WechatJsapiConfig config =AbstractConfig.loadConfig(WechatJsapiConfig.class);

	/**
	 * 发起退款申请
	 * 
	 * @param model
	 * @return
	 * @throws MutilsErrorException
	 * @throws IOException 
	 * @throws JDOMException 
	 * @throws ClientProtocolException 
	 */
	public static RefundReturnModel createJsapiRefundParamter(JsapiRefundModel model)
			throws MutilsErrorException, ClientProtocolException, JDOMException, IOException {
		
		return createRefundRequest(model);
	}

	/**
	 * 生成微信JS相关初始化config配置
	 * 
	 * @param url    当前网页地址
	 * @param debug  是否开启调试
	 * @param 要使用的功能 默认 "openLocation", "getLocation", "chooseWXPay"
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static Map<String, Object> createInitJSConfig(String url, boolean debug, String... functions) throws ClientProtocolException, IOException{
		
			if (functions == null||functions.length==0) {
				functions = new String[] { "openLocation", "getLocation", "chooseWXPay" };
			}
			String jsapi_ticket = getAccessToken().getJsapi_ticket();
			// 10位序列号,可以自行调整。
			String nonce_str = String.valueOf(System.currentTimeMillis());
			
			Long timestamp = System.currentTimeMillis() / 1000;
			
			SortedMap<String, String> packageParams = new TreeMap<>();
			packageParams.put("noncestr", nonce_str);
			packageParams.put("timestamp", timestamp.toString());
			packageParams.put("jsapi_ticket", jsapi_ticket);
			packageParams.put("url", url);
			String sign = Sha1Util.createSHA1Sign(packageParams);
			SortedMap<String, Object> returnMap = new TreeMap<>();
			returnMap.put("appId", config.getAppid());
			returnMap.put("nonceStr", nonce_str);
			returnMap.put("timestamp", timestamp);
			returnMap.put("signature", sign);
			returnMap.put("jsApiList", functions);
			returnMap.put("debug", debug);
			return returnMap;
	}

	/**
	 * jsapi获取AccessToken用于实现登录
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static AccessTokenModel getAccessToken() throws ClientProtocolException, IOException{
		
		CloseableHttpClient instance = HttpClientUtil.getInstance();
		CloseableHttpResponse response =null;
		try {
			String accessTokenUrl = config.getAccessTokenUrl();

			String jsapiTicketUrl = config.getJsapiTicketUrl();

			String appid = config.getAppid();

			String appSecret = config.getAppSecret();

			String requestUrl = accessTokenUrl.replace("APPID", appid).replace("APPSECRET", appSecret);

			HttpGet getMethod = HttpClientUtil.getGetMethod(requestUrl);
			response = instance.execute(getMethod);
			String string = EntityUtils.toString(response.getEntity(), "UTF-8");
			getMethod.releaseConnection();
			JSONObject jsonObject = JSON.parseObject(string);
			String access_token = jsonObject.get("access_token").toString();
			String jsapi_ticketurl = jsapiTicketUrl.replace("ACCESS_TOKEN", access_token);
			getMethod = HttpClientUtil.getGetMethod(jsapi_ticketurl);
			string = EntityUtils.toString(response.getEntity(), "UTF-8");
			getMethod.releaseConnection();
			jsonObject = JSON.parseObject(string);
			String jsapi_ticke = jsonObject.get("ticket").toString();
			AccessTokenModel accessToken = new AccessTokenModel();
			accessToken.setAccess_token(access_token);
			accessToken.setExpires_in(7200);
			accessToken.setExpires_time(System.currentTimeMillis() / 1000);
			accessToken.setJsapi_ticket(jsapi_ticke);
			return accessToken;
		}finally {
			IOUtil.close(instance,response);
		}
	}

	/**
	 * 创建公众号支付的请求参数 小程序将用其发起微信支付
	 * 
	 * @param model 下单时的包装对象
	 * @return 公众号能发起的请求的包装内容
	 * @throws JDOMException 
	 * @throws IOException 
	 * @throws ParseException 
	 */
	public static Map<String, String> createJsapiPayParamter(JsapiOrderPayModel model) throws ParseException, IOException, MutilsErrorException, JDOMException {
		
			Map<String, String> doXMLParse = createUnifiedOrder(model);
			checkMap(doXMLParse);
			SortedMap<String, String> sortMap = new TreeMap<>();
			String appId = doXMLParse.get("appid");
			sortMap.put("appId", appId);
			sortMap.put("nonceStr", doXMLParse.get("nonce_str"));
			sortMap.put("package", "prepay_id=" + doXMLParse.get("prepay_id"));
			sortMap.put("signType", "MD5");
			sortMap.put("timeStamp", System.currentTimeMillis() / 1000 + "");
			sortMap.put("paySign", createSign(sortMap));
			sortMap.put("prepay_id", doXMLParse.get("prepay_id"));
			return sortMap;

	}
}
