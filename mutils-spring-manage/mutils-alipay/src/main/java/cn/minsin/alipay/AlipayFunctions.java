package cn.minsin.alipay;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;

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
 * 	支付宝功能列表
 * @author mintonzhang
 * @date 2019年1月17日
 * @since 0.1.0
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
	public static AlipayTradePrecreateResponse createWebAlipayParams(PayModel payModel) throws MutilsErrorException {

		try {
			AlipayTradePrecreateRequest alipayRequest = new AlipayTradePrecreateRequest();
			alipayRequest.setBizContent(payModel.toString());
			alipayRequest.setNotifyUrl(config.getNotifyUrl());// 设置回调地址
			 return  initAlipayClient().execute(alipayRequest);
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
	public static AlipayTradeAppPayResponse createAlipayParams(PayModel payModel) throws MutilsErrorException {
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
	public static AlipayFundTransToaccountTransferResponse transfer(TransferModel model) throws MutilsErrorException {

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
	 * 退款是需要验证public_key 请确认是否和sign_type匹配
	 * @param model
	 * @return
	 * @throws MutilsErrorException
	 */
	public static AlipayTradeRefundResponse refund(RefundModel model) throws MutilsErrorException {

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
	public static NotifyModel parseNotify(HttpServletRequest req) throws MutilsErrorException {
		try {
			req.setCharacterEncoding("utf-8");
			VO init = VO.init();
			Map<String, String[]> requestParams = req.getParameterMap();
			for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
				}
				init.put(name, valueStr);
			}
			return init.toObject(NotifyModel.class);
		} catch (Exception e) {
			throw new MutilsErrorException(e, "Parsing alipay callback failed.");
		}
	}

	protected static AlipayClient initAlipayClient() {
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
