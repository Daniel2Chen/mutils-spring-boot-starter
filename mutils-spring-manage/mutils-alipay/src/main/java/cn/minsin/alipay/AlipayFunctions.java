package cn.minsin.alipay;

import java.io.UnsupportedEncodingException;
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
import cn.minsin.core.init.core.AbstractConfig;
import cn.minsin.core.rule.AbstractFunctionRule;
import cn.minsin.core.tools.ModelUtil;
import cn.minsin.core.web.VO;

/**
 * 支付宝功能列表
 * 
 * @author mintonzhang
 * @date 2019年1月17日
 * @since 0.1.0
 */
public class AlipayFunctions extends AbstractFunctionRule {

	private final static AlipayConfig config = AbstractConfig.loadConfig(AlipayConfig.class);

	/**
	 * 发起支付宝网站支付
	 * 
	 * @return
	 * @throws MutilsErrorException
	 * @throws AlipayApiException
	 */
	public static AlipayTradePrecreateResponse createWebAlipayParams(PayModel payModel) throws AlipayApiException {
		ModelUtil.verificationField(payModel);
		AlipayTradePrecreateRequest alipayRequest = new AlipayTradePrecreateRequest();
		alipayRequest.setBizContent(payModel.toString());
		alipayRequest.setNotifyUrl(config.getNotifyUrl());
		return initAlipayClient().execute(alipayRequest);
	}

	/**
	 * 发起支付宝订单生成
	 * 
	 * @return
	 * @throws AlipayApiException
	 */
	public static AlipayTradeAppPayResponse createAlipayParams(PayModel payModel) throws AlipayApiException {
		ModelUtil.verificationField(payModel);
		AlipayTradeAppPayRequest alipayRequest = new AlipayTradeAppPayRequest();
		alipayRequest.setBizContent(payModel.toString());
		alipayRequest.setNotifyUrl(config.getNotifyUrl());
		return initAlipayClient().sdkExecute(alipayRequest);

	}

	/**
	 * 支付宝转账
	 * 
	 * @param model
	 * @return 2018年12月6日
	 * @throws AlipayApiException
	 */
	public static AlipayFundTransToaccountTransferResponse transfer(TransferModel model) throws AlipayApiException {
		ModelUtil.verificationField(model);
		AlipayFundTransToaccountTransferRequest alipayRequest = new AlipayFundTransToaccountTransferRequest();
		alipayRequest.setBizContent(model.toString());
		return initAlipayClient().execute(alipayRequest);
	}

	/**
	 * 支付宝退款 退款是需要验证public_key 请确认是否和sign_type匹配
	 * 
	 * @param model
	 * @return
	 * @throws AlipayApiException
	 */
	public static AlipayTradeRefundResponse refund(RefundModel model) throws AlipayApiException {
		ModelUtil.verificationField(model);
		AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();
		alipayRequest.setBizContent(model.toString());
		return initAlipayClient().execute(alipayRequest);
	}

	/**
	 * 解析支付宝回调并验证签名
	 * 
	 * 如果成功 使用PrintWriter.println输出 success 如果失败输出failed 反馈给支付宝服务器不用再重复请求。
	 * 
	 * @param req
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static NotifyModel parseNotify(HttpServletRequest req) throws UnsupportedEncodingException {
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
	}

	protected static AlipayClient initAlipayClient() {
		return new DefaultAlipayClient(config.getServerUrl(), config.getAppid(), config.getPrivateKey(),
				config.getFormat(), config.getCharset(), config.getPublicKey(), config.getSignType());
	}
}
