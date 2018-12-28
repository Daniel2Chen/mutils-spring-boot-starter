package cn.minsin.alipay;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;

import cn.minsin.core.exception.MutilsErrorException;
import cn.minsin.core.init.AlipayConfig;
import cn.minsin.core.rule.FunctionRule;
import cn.minsin.core.web.VO;

/**
 * 集成支付宝常用功能
 * 
 * @author mintonzhang@163.com 2018年12月6日
 */
public class AlipayFunctions extends FunctionRule {

	/**
	 * 发起支付宝网站支付
	 * 
	 * @param out_trade_no
	 * @param price
	 * @return
	 */
	public static String createWebAlipayParams(String out_trade_no, BigDecimal price, String title)
			throws MutilsErrorException {
		try {
			AlipayTradePrecreateRequest alipayRequest = new AlipayTradePrecreateRequest();
			Map<String, Object> map = new HashMap<>();
			map.put("subject", title);
			map.put("out_trade_no", out_trade_no);
			map.put("total_amount", price);
			alipayRequest.setBizContent(JSON.toJSONString(map));
			alipayRequest.setNotifyUrl(AlipayConfig.alipayConfig.getNotifyUrl());// 设置回调地址
			map.clear();
			map.put("orderNum", out_trade_no);// 订单号
			AlipayTradePrecreateResponse response = initAlipayClient().execute(alipayRequest);
			if (response.isSuccess()) {
				return response.getQrCode();
			}
			return null;
		} catch (Exception e) {
			throw new MutilsErrorException(e, "支付宝创建网页支付失败");
		}
	}

	/**
	 * 创建app订单
	 * 
	 * @param out_trade_no
	 * @param price
	 * @return 2018年7月18日
	 */
	public static String createAlipayParams(String out_trade_no, BigDecimal price, String title)
			throws MutilsErrorException {
		try {
			// 进行保留两位小数
			price = price.setScale(2, RoundingMode.DOWN);
			AlipayTradeAppPayRequest alipayRequest = new AlipayTradeAppPayRequest();
			AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
			model.setOutTradeNo(out_trade_no);
			model.setTotalAmount(price.toString());
			model.setSubject(title);
			alipayRequest.setBizModel(model);
			alipayRequest.setNotifyUrl(AlipayConfig.alipayConfig.getNotifyUrl());// 设置回调地址
			AlipayTradeAppPayResponse response = initAlipayClient().sdkExecute(alipayRequest);
			if (response.isSuccess()) {
				return response.getBody();
			}
			return null;
		} catch (AlipayApiException e) {
			throw new MutilsErrorException(e, "支付宝创建APP支付失败");
		}
	}

	/**
	 * 支付宝转账
	 * 
	 * @param model
	 * @return 2018年12月6日
	 * @author mintonzhang@163.com
	 * @throws MutilsErrorException
	 */
	public static VO transfer(TransferModel model) throws MutilsErrorException {
		AlipayFundTransToaccountTransferRequest alipayRequest = new AlipayFundTransToaccountTransferRequest();
		alipayRequest.setBizContent(JSON.toJSONString(model));
		AlipayFundTransToaccountTransferResponse response = null;
		try {
			response = initAlipayClient().execute(alipayRequest);
			return VO.builder().put("code", response.getCode()).put("sub_msg", response.getSubMsg());
		} catch (AlipayApiException e) {
			throw new MutilsErrorException(e, "支付宝转账失败");
		}
	}

	static AlipayClient initAlipayClient() {
		return new DefaultAlipayClient(AlipayConfig.alipayConfig.getServerUrl(), AlipayConfig.alipayConfig.getAppid(),
				AlipayConfig.alipayConfig.getPrivateKey(), AlipayConfig.alipayConfig.getFormat(),
				AlipayConfig.alipayConfig.getCharset(), AlipayConfig.alipayConfig.getPublicKey(),
				AlipayConfig.alipayConfig.getSignType());
	}
}
