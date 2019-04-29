package cn.mutils.meituan.peisong;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import cn.minsin.core.exception.MutilsErrorException;
import cn.minsin.core.rule.AbstractFunctionRule;
import cn.minsin.core.tools.HttpClientUtil;
import cn.mutils.meituan.peisong.model.receive.ReceiveInfoModel;
import cn.mutils.meituan.peisong.model.send.OrderMockModel;

/**
 * 美团订单模拟， 仅限于测试环境
 * 
 * @author mintonzhang
 * @date 2019年2月19日
 * @since 0.3.4
 */
public class MeituanPeisongMockFunctions extends AbstractFunctionRule {

	/**
	 * 模拟接单
	 * 
	 * @param model 模拟订单实体
	 * @return
	 * @throws IOException
	 * @throws MutilsErrorException
	 * @throws NoSuchAlgorithmException
	 */
	public static ReceiveInfoModel acceptOrder(OrderMockModel model)
			throws IOException, NoSuchAlgorithmException, MutilsErrorException {
		Map<String, String> params = model.toMap();
		String serverUrl = model.getServerUrl() + "/test/order/arrange";
		String post = HttpClientUtil.post(serverUrl, params);
		return JSON.parseObject(post, ReceiveInfoModel.class);
	}

	/**
	 * 模拟取货
	 * 
	 * @param model 模拟订单实体
	 * @return
	 * @throws IOException
	 * @throws MutilsErrorException
	 * @throws NoSuchAlgorithmException
	 */
	public static ReceiveInfoModel pickup(OrderMockModel model)
			throws IOException, NoSuchAlgorithmException, MutilsErrorException {
		Map<String, String> params = model.toMap();
		String serverUrl = model.getServerUrl() + "/test/order/pickup";
		String post = HttpClientUtil.post(serverUrl, params);
		return JSON.parseObject(post, ReceiveInfoModel.class);
	}

	/**
	 * 模拟订单完成
	 * 
	 * @param model 模拟订单实体
	 * @return
	 * @throws IOException
	 * @throws MutilsErrorException
	 * @throws NoSuchAlgorithmException
	 */
	public static ReceiveInfoModel deliver(OrderMockModel model)
			throws IOException, NoSuchAlgorithmException, MutilsErrorException {
		Map<String, String> params = model.toMap();
		String serverUrl = model.getServerUrl() + "/test/order/deliver";
		String post = HttpClientUtil.post(serverUrl, params);
		return JSON.parseObject(post, ReceiveInfoModel.class);
	}

	/**
	 * 模拟测试订单改派
	 * 
	 * @param model 模拟订单实体
	 * @return
	 * @throws IOException
	 * @throws MutilsErrorException
	 * @throws NoSuchAlgorithmException
	 */
	public static ReceiveInfoModel rearrange(OrderMockModel model)
			throws IOException, NoSuchAlgorithmException, MutilsErrorException {
		Map<String, String> params = model.toMap();
		String serverUrl = model.getServerUrl() + "/test/order/rearrange";
		String post = HttpClientUtil.post(serverUrl, params);
		return JSON.parseObject(post, ReceiveInfoModel.class);
	}

}
