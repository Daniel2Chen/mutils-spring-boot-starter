package org.mutils.wechat.app;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.mutils.wechat.app.model.AppOrderPayModel;
import org.mutils.wechat.app.model.AppRefundModel;
import org.mutils.wechat.wechatpay.core.WeChatPayFunctions;
import cn.minsin.core.exception.MutilsErrorException;

/**
 * APP 移动端的API
 * @author mintonzhang
 * @date 2019年1月10日
 */
public class WechatAppFunctions extends WeChatPayFunctions {

	
	/**
	 * 创建APP支付的请求参数 APP将用其发起微信支付
	 * 
	 * @param model 下单时的包装对象
	 * @return APP能发起的请求的包装内容
	 * @throws MutilsErrorException
	 */
	public static Map<String, String> createAppPayParamter(AppOrderPayModel model) throws MutilsErrorException {
		try {
			Map<String, String> doXMLParse = createUnifiedOrder(model);
			checkMap(doXMLParse);
			SortedMap<String, String> sortMap = new TreeMap<>();
			String appId = doXMLParse.get("appid");
			String nonceStr = doXMLParse.get("nonce_str");
			String prepayid = doXMLParse.get("prepay_id");
			String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
			sortMap.put("appid", appId);
			sortMap.put("partnerid", payconfig.getPartnerId());
			sortMap.put("noncestr", nonceStr);
			sortMap.put("package", "Sign=WXPay");
			sortMap.put("timestamp", timeStamp);
			sortMap.put("prepayid", prepayid);
			sortMap.put("sign", createSign(sortMap));
			return sortMap;
		} catch (Exception e) {
			throw new MutilsErrorException(e, "发起APP支付失败");
		}
	}
	
	/**
	 * 发起退款申请
	 * 
	 * @param model
	 * @return
	 * @throws MutilsErrorException
	 */
	public static Map<String, String> createMiniProgramRefundParamter(AppRefundModel model) throws MutilsErrorException {
		return createRefundRequest(model);
	}
}
