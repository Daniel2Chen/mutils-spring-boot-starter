package cn.mutils.meituan.peisong;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;

import cn.minsin.core.exception.MutilsErrorException;
import cn.minsin.core.rule.AbstractFunctionRule;
import cn.minsin.core.tools.HttpClientUtil;
import cn.minsin.core.web.VO;
import cn.mutils.meituan.peisong.model.notify.AbstractMeituanNotifyModel;
import cn.mutils.meituan.peisong.model.notify.OrderExceptionNotifyModel;
import cn.mutils.meituan.peisong.model.notify.OrderStateNotifyModel;
import cn.mutils.meituan.peisong.model.notify.ShopRangeChangeNotifyModel;
import cn.mutils.meituan.peisong.model.receive.ReceiveInfoModel;
import cn.mutils.meituan.peisong.model.receive.ReceiveQueryInfoModel;
import cn.mutils.meituan.peisong.model.receive.ReceiveRiderLocationModel;
import cn.mutils.meituan.peisong.model.send.CheckShopModel;
import cn.mutils.meituan.peisong.model.send.OrderByCoordinatesModel;
import cn.mutils.meituan.peisong.model.send.OrderByShopModel;
import cn.mutils.meituan.peisong.model.send.OrderCancelModel;
import cn.mutils.meituan.peisong.model.send.QueryOrderModel;
import cn.mutils.meituan.peisong.model.send.RiderEvaluateModel;

/**
 * 	美团配送功能列表
 * 
 * @author mintonzhang
 * @date 2019年2月18日
 * @since 0.3.4
 */
public class MeituanPeisongFunctions extends AbstractFunctionRule {

	/**
	 * 合作方根据已录入的门店信息 将订单发送给美团配送平台。
	 * 
	 * @param model 预下单实体
	 * @return
	 * @throws IOException
	 * @throws MutilsErrorException
	 * @throws NoSuchAlgorithmException
	 * @throws Exception
	 */
	public static ReceiveInfoModel createByShop(OrderByShopModel model)
			throws IOException, NoSuchAlgorithmException, MutilsErrorException {
		Map<String, String> params = model.toMap();
		String serverUrl = model.getServerUrl() + "/order/createByShop";
		String post = HttpClientUtil.post(serverUrl, params);
		return JSON.parseObject(post, ReceiveInfoModel.class);
	}

	/**
	 * 根据合作方的送件地址，自动选择合适的中间仓库作为线下货物的交接地点，并完成末端配送。
	 * 
	 * @param model 预下单实体
	 * @return
	 * @throws IOException
	 * @throws MutilsErrorException
	 * @throws NoSuchAlgorithmException
	 * @throws Exception
	 */
	public static ReceiveInfoModel createByCoordinates(OrderByCoordinatesModel model)
			throws IOException, NoSuchAlgorithmException, MutilsErrorException {
		Map<String, String> params = model.toMap();
		String serverUrl = model.getServerUrl() + "/order/createByCoordinates";
		String post = HttpClientUtil.post(serverUrl, params);
		return JSON.parseObject(post, ReceiveInfoModel.class);
	}

	/**
	 * 查询订单
	 * 
	 * @param model 订单查询model
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws MutilsErrorException
	 * @throws IOException
	 */
	public static ReceiveQueryInfoModel queryOrder(QueryOrderModel model)
			throws NoSuchAlgorithmException, MutilsErrorException, IOException {
		Map<String, String> params = model.toMap();
		String serverUrl = model.getServerUrl() + "/order/status/query";
		String post = HttpClientUtil.post(serverUrl, params);
		return JSON.parseObject(post, ReceiveQueryInfoModel.class);
	}

	/**
	 * 获取骑手位置
	 * 
	 * @param model 订单查询model
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws MutilsErrorException
	 * @throws IOException
	 */
	public static ReceiveRiderLocationModel getRiderLocation(QueryOrderModel model)
			throws NoSuchAlgorithmException, MutilsErrorException, IOException {
		Map<String, String> params = model.toMap();
		String serverUrl = model.getServerUrl() + "/order/rider/location";
		String post = HttpClientUtil.post(serverUrl, params);
		return JSON.parseObject(post, ReceiveRiderLocationModel.class);
	}

	/**
	 * 取消订单
	 * 
	 * @param model 订单查询model
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws MutilsErrorException
	 * @throws IOException
	 */
	public static ReceiveInfoModel cancelOrder(OrderCancelModel model)
			throws NoSuchAlgorithmException, MutilsErrorException, IOException {
		Map<String, String> params = model.toMap();
		String serverUrl = model.getServerUrl() + "/order/delete";
		String post = HttpClientUtil.post(serverUrl, params);
		return JSON.parseObject(post, ReceiveInfoModel.class);
	}

	/**
	 * 评价骑手
	 * 
	 * @param model 骑手评价model
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws MutilsErrorException
	 * @throws IOException
	 */
	public static ReceiveInfoModel riderEvaluate(RiderEvaluateModel model)
			throws NoSuchAlgorithmException, MutilsErrorException, IOException {
		Map<String, String> params = model.toMap();
		String serverUrl = model.getServerUrl() + "/order/evaluate";
		String post = HttpClientUtil.post(serverUrl, params);
		return JSON.parseObject(post, ReceiveInfoModel.class);
	}

	/**
	 * 配送能力校验 根据合作方提供的模拟发单参数，确定美团是否可配送。主要校验项：门店是否存在、门店配送范围、门店营业时间、门店支持的服务包。
	 * 
	 * @param model 校验门店model
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws MutilsErrorException
	 * @throws IOException
	 */
	public static ReceiveInfoModel checkShop(CheckShopModel model)
			throws NoSuchAlgorithmException, MutilsErrorException, IOException {
		Map<String, String> params = model.toMap();
		String serverUrl = model.getServerUrl() + "/order/check";
		String post = HttpClientUtil.post(serverUrl, params);
		return JSON.parseObject(post, ReceiveInfoModel.class);
	}

	/**
	 * 解析订单状态回调 每次订单状态发生变化时，会对合作方提供的回调url进行回调。 详见
	 * ：https://page.peisong.meituan.com/open/doc#section2-5
	 * 
	 * @param req
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static OrderExceptionNotifyModel parseExceptionNotify(HttpServletRequest req)
			throws UnsupportedEncodingException {
		return parseNotify(OrderExceptionNotifyModel.class, req);
	}

	/**
	 * 订单异常回调 每次配送员上报订单异常（如商家未营业、顾客留错电话等等）时，会对合作方提供的异常回调url进行回调。 详见
	 * ：https://page.peisong.meituan.com/open/doc#section2-6
	 * 
	 * @param req
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static OrderStateNotifyModel parseOrderStateNotify(HttpServletRequest req)
			throws UnsupportedEncodingException {
		return parseNotify(OrderStateNotifyModel.class, req);
	}

	/**
	 * 配送范围变更回调 每次美团运营新增、修改配送范围时，会对合作方提供的配送范围变更回调url进行回调。 详见
	 * ：https://page.peisong.meituan.com/open/doc#section2-11
	 * 
	 * @param req
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static ShopRangeChangeNotifyModel parseShopRangeChangeNotify(HttpServletRequest req)
			throws UnsupportedEncodingException {
		return parseNotify(ShopRangeChangeNotifyModel.class, req);
	}

	protected static <T extends AbstractMeituanNotifyModel> T parseNotify(Class<T> clazz, HttpServletRequest req)
			throws UnsupportedEncodingException {
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
		return init.toObject(clazz);
	}

}
