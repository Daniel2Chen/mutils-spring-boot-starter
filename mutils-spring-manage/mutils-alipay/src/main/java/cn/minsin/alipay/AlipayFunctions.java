package cn.minsin.alipay;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.AlipayResponse;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;

import cn.minsin.alipay.model.NotifyModel;
import cn.minsin.alipay.model.PayModel;
import cn.minsin.alipay.model.RefundModel;
import cn.minsin.alipay.model.TransferModel;
import cn.minsin.core.exception.MutilsErrorException;
import cn.minsin.core.init.AlipayConfig;
import cn.minsin.core.init.core.InitConfig;
import cn.minsin.core.rule.FunctionRule;
import cn.minsin.core.web.VO;

/**
 * 集成支付宝常用功能
 * 
 * @author mintonzhang@163.com 2018年12月6日
 */
public class AlipayFunctions extends FunctionRule {

	private final static AlipayConfig config = InitConfig.loadConfig(AlipayConfig.class);

	/**
	 * 发起支付宝网站支付
	 * 
	 * @param out_trade_no
	 * @param price
	 * @return
	 * @throws MutilsErrorException
	 */
	public static AlipayResponse createWebAlipayParams(PayModel payModel) throws MutilsErrorException {

		try {
			AlipayTradePrecreateRequest alipayRequest = new AlipayTradePrecreateRequest();
			alipayRequest.setBizContent(payModel.toString());
			alipayRequest.setNotifyUrl(config.getNotifyUrl());// 设置回调地址
			return initAlipayClient().execute(alipayRequest);
		} catch (Exception e) {
			throw new MutilsErrorException(e, "Create Alipay Web payment failure.");
		}
	}

	/**
	 * 发起支付宝订单生成
	 * 
	 * @param out_trade_no
	 * @param price
	 * @return 2018年7月18日
	 * @throws MutilsErrorException
	 */
	public static AlipayResponse createAlipayParams(PayModel payModel) throws MutilsErrorException {
		try {
			// 进行保留两位小数
			AlipayTradeAppPayRequest alipayRequest = new AlipayTradeAppPayRequest();
			alipayRequest.setBizContent(payModel.toString());
			alipayRequest.setNotifyUrl(config.getNotifyUrl());// 设置回调地址
			return initAlipayClient().sdkExecute(alipayRequest);
		} catch (AlipayApiException e) {
			throw new MutilsErrorException(e, "Create Alipay mobile payment failure.");
		}

	}

	/**
	 * 支付宝转账
	 * 
	 * @param model
	 * @return 2018年12月6日
	 * @throws MutilsErrorException
	 */
	public static AlipayResponse transfer(TransferModel model) throws MutilsErrorException {

		try {
			AlipayFundTransToaccountTransferRequest alipayRequest = new AlipayFundTransToaccountTransferRequest();
			alipayRequest.setBizContent(model.toString());
			return initAlipayClient().execute(alipayRequest);
		} catch (AlipayApiException e) {
			throw new MutilsErrorException(e, "Initiation of Alipay transfer failed.");
		}
	}

	/**
	 * 支付宝退款
	 * 
	 * @param model
	 * @return
	 * @throws MutilsErrorException
	 */
	public static AlipayResponse refund(RefundModel model) throws MutilsErrorException {

		try {
			AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();
			alipayRequest.setBizContent(model.toString());
			return initAlipayClient().execute(alipayRequest);
		} catch (Exception e) {
			throw new MutilsErrorException(e, "Initiating Alipay refund failed.");
		}
	}

	/**
	 * 解析支付宝回调并验证签名
	 * 
	 * 如果成功 使用PrintWriter.println输出 success 如果失败输出failed 反馈给支付宝服务器不用再重复请求。
	 * 
	 * @param req
	 * @return
	 * @throws MutilsErrorException
	 */
	public static NotifyModel parseNotify(final HttpServletRequest req) throws MutilsErrorException {
		try {
			req.setCharacterEncoding("utf-8");
			Map<String, String> conversionParams = new HashMap<String, String>();
			VO init = VO.init();
			Map<String, String[]> requestParams = req.getParameterMap();
			for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				conversionParams.put(name, valueStr);
				init.put(name, valueStr);
			}
			// 验证签名
			if (AlipaySignature.rsaCheckV1(conversionParams, config.getPublicKey(), config.getCharset(),
					config.getSignType())) {
				return init.toObject(NotifyModel.class);
			}
			throw new MutilsErrorException("签名验证失败");
		} catch (Exception e) {
			throw new MutilsErrorException(e, "支付宝回调解析失败");
		}
	}

	static AlipayClient initAlipayClient() {
		return new DefaultAlipayClient(
				config.getServerUrl(),
				config.getAppid(), 
				config.getPrivateKey(),
				config.getFormat(),
				config.getCharset(), 
				config.getPublicKey(), 
				config.getSignType());
	}
}
