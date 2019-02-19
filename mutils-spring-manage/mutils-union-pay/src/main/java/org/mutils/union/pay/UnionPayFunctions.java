package org.mutils.union.pay;

import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;

import javax.servlet.http.HttpServletRequest;

import org.mutils.union.pay.model.UnionPayModel;
import org.mutils.union.pay.util.AcpService;
import org.mutils.union.pay.util.SDKConstants;

import cn.minsin.core.exception.MutilsErrorException;
import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.init.UnionPayConfig;
import cn.minsin.core.init.core.AbstractConfig;
import cn.minsin.core.rule.AbstractFunctionRule;
import cn.minsin.core.tools.StringUtil;

/**
 * 银联支付功能列表
 * 
 * @author mintonzhang
 * @date 2019年1月16日
 * @since 0.2.3
 */
public class UnionPayFunctions extends AbstractFunctionRule {

	private final static UnionPayConfig config = AbstractConfig.loadConfig(UnionPayConfig.class);

	/**
	 * 银联支付返回一个form表单
	 * 
	 * @param model 下单对象
	 * @return
	 * @throws MutilsErrorException
	 */
	public static String unionPay(UnionPayModel model) throws MutilsErrorException {
		SortedMap<String, String> requestData = model.toTreeMap();
		Map<String, String> submitFromData = AcpService.sign(requestData); // 报文中certId,signature的值是在signData方法中获取并自动赋值的，只要证书配置正确即可。

		String requestFrontUrl = config.getFrontRequestUrl(); // 获取请求银联的前台地址：对应属性文件acp_sdk.properties文件中的acpsdk.frontTransUrl
		String form = AcpService.createAutoFormHtml(requestFrontUrl, submitFromData); // 生成自动跳转的Html表单
		LOGGER.info("打印请求HTML，此为请求报文，为联调排查问题的依据：{}", form);
		// 将生成的html写到浏览器中完成自动跳转打开银联支付页面；这里调用signData之后，将html写到浏览器跳转到银联页面之前均不能对html中的表单项的名称和值进行修改，如果修改会导致验签不通过
		// resp.getWriter().write(html);
		return form;

	}

	/**
	 * 银联回调解析 //response.getWriter().print("ok");//返回给银联服务器http 200 状态码
	 * 
	 * @param request 银联回调
	 * @return
	 * @throws MutilsErrorException
	 */
	public static Map<String, String> parseNotify(HttpServletRequest request) throws MutilsErrorException {
		String encoding = request.getParameter(SDKConstants.param_encoding);
		Map<String, String> res = new HashMap<String, String>();
		Enumeration<String> temp = request.getParameterNames();
		if (null != temp) {
			while (temp.hasMoreElements()) {
				String en = temp.nextElement();
				String value = request.getParameter(en);
				String string = res.get(en);
				if (StringUtil.isNotBlank(string)) {
					res.put(en, value);
				}
			}
		}
		Map<String, String> valideData = null;
		if (null != res && !res.isEmpty()) {
			Iterator<Entry<String, String>> it = res.entrySet().iterator();
			valideData = new HashMap<String, String>(res.size());
			while (it.hasNext()) {
				Entry<String, String> e = it.next();
				String key = e.getKey();
				String value = e.getValue();
				try {
					value = new String(value.getBytes(encoding), encoding);
				} catch (UnsupportedEncodingException e1) {
					throw new MutilsException(e1, key + " Conversion to " + encoding + " failed.");
				}
				valideData.put(key, value);
			}
		}
		if (AcpService.validate(valideData, encoding)) {
			return valideData;
		}
		throw new MutilsErrorException("签名验证失败");
	}
}
