package org.mutils.wechat.miniprogram;

import java.io.InputStream;
import java.security.AlgorithmParameters;
import java.security.Security;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Arrays;
import org.bouncycastle.util.encoders.Base64;
import org.mutils.wechat.miniprogram.model.AccessTokenModel;
import org.mutils.wechat.miniprogram.model.Code2SessionReturnModel;
import org.mutils.wechat.miniprogram.model.MiniProgramCodeModel;
import org.mutils.wechat.miniprogram.model.MiniProgramOrderPayModel;
import org.mutils.wechat.miniprogram.model.MiniProgramRefundModel;
import org.mutils.wechat.miniprogram.model.UserInfoModel;
import org.mutils.wechat.wechatpay.core.WeChatPayFunctions;
import org.mutils.wechat.wechatpay.core.model.RefundReturnModel;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.minsin.core.exception.MutilsErrorException;
import cn.minsin.core.init.WechatMiniProgramConfig;
import cn.minsin.core.init.core.InitConfig;
import cn.minsin.core.tools.HttpClientUtil;
import cn.minsin.core.tools.IOUtil;

/**
 * 小程序相关功能
 * 
 * @author mintonzhang
 * @date 2019年1月10日
 */
public class WechatMiniProgramFunctions extends WeChatPayFunctions {

	private static final WechatMiniProgramConfig config = InitConfig.loadConfig(WechatMiniProgramConfig.class);

	/**
	 * 获取sessionkey和openid,一般用于小程序授权登录.
	 * 官方文档 https://developers.weixin.qq.com/miniprogram/dev/api/code2Session.html
	 * @param code 小程序获取的code
	 * @return
	 * @throws MutilsErrorException
	 */
	public static Code2SessionReturnModel jscode2session(String code) throws MutilsErrorException {
		CloseableHttpClient build = HttpClientUtil.getInstance();
		CloseableHttpResponse response = null;
		try {
			String url = "https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code";

			url = url.replace("APPID", config.getAppid()).replace("SECRET", config.getAppSecret()).replace("JSCODE",
					code);
			HttpGet get = HttpClientUtil.getGetMethod(url);

			response = build.execute(get);
			HttpEntity entity = response.getEntity();
			String string = EntityUtils.toString(entity);
			log.info("Code2SessionReturnModel string is {}", string);
			return JSON.parseObject(string, Code2SessionReturnModel.class);
		} catch (Exception e) {
			throw new MutilsErrorException(e, "小程序使用code换取openid等信息失败");
		} finally {
			IOUtil.close(build, response);
		}
	}

	/**
	 * 解密用户敏感数据获取用户信息 注意wx.login() 必须要在wx.getUserinfo()前调用
	 * 
	 * @param sessionKey    数据进行加密签名的密钥
	 * @param encryptedData 包括敏感数据在内的完整用户信息的加密数据
	 * @param iv            加密算法的初始向量
	 * @throws MutilsErrorException
	 */
	public static UserInfoModel getUserInfo(String encryptedData, String code, String iv) throws MutilsErrorException {
		try {
			Code2SessionReturnModel jscode2session = jscode2session(code);
			// 被加密的数据
			byte[] dataByte = Base64.decode(encryptedData);
			// 加密秘钥
			byte[] keyByte = Base64.decode(jscode2session.getSession_key());
			// 偏移量
			byte[] ivByte = Base64.decode(iv);

			// 如果密钥不足16位，那么就补足. 这个if 中的内容很重要
			int base = 16;
			if (keyByte.length % base != 0) {
				int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
				byte[] temp = new byte[groups * base];
				Arrays.fill(temp, (byte) 0);
				System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
				keyByte = temp;
			}
			// 初始化
			Security.addProvider(new BouncyCastleProvider());
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
			SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
			AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
			parameters.init(new IvParameterSpec(ivByte));
			cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化
			byte[] resultByte = cipher.doFinal(dataByte);
			String result = new String(resultByte, "UTF-8");
			return JSONObject.parseObject(result, UserInfoModel.class);
		} catch (Exception e) {
			throw new MutilsErrorException(e, "解析小程序密文失败");
		}
	}

	/**
	 * 创建小程序支付的请求参数 小程序将用其发起微信支付 注意：小程序必须要要使用填写openid
	 * 参考 https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4
	 * @param model 下单时的包装对象
	 * @return 小程序能发起的请求的包装内容
	 * @throws MutilsErrorException
	 */
	public static Map<String, String> createMiniProgramPayParamter(MiniProgramOrderPayModel model)
			throws MutilsErrorException {
		try {
			Map<String, String> doXMLParse = createUnifiedOrder(model);
			checkMap(doXMLParse);
			SortedMap<String, String> sortMap = new TreeMap<>();
			String appId = doXMLParse.get("appid");
			String nonceStr = doXMLParse.get("nonce_str");
			String package_str = "prepay_id=" + doXMLParse.get("prepay_id");
			String signType = "MD5";
			String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);
			sortMap.put("appId", appId);
			sortMap.put("nonceStr", nonceStr);
			sortMap.put("package", package_str);
			sortMap.put("signType", signType);
			sortMap.put("timeStamp", timeStamp);
			sortMap.put("paySign", createSign(sortMap));
			sortMap.remove("appId");
			return sortMap;
		} catch (Exception e) {
			throw new MutilsErrorException(e, "发起小程序支付失败");
		}
	}

	/**
	 * 发起退款申请
	 * 参考 https://pay.weixin.qq.com/wiki/doc/api/jsapi.php?chapter=9_4
	 * @param model
	 * @return
	 * @throws MutilsErrorException
	 */
	public static RefundReturnModel createMiniProgramRefundParamter(MiniProgramRefundModel model)
			throws MutilsErrorException {
		return createRefundRequest(model);
	}

	/**
	 * 小程序获取accessToken
	 * 详情参考 https://developers.weixin.qq.com/miniprogram/dev/api/getAccessToken.html
	 * @return
	 * @throws MutilsErrorException
	 */
	public static AccessTokenModel getAccessToken() throws MutilsErrorException {
		CloseableHttpClient instance = HttpClientUtil.getInstance();
		CloseableHttpResponse response = null;
		try {
			String accessTokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
			String requestUrl = accessTokenUrl.replace("APPID", config.getAppid()).replace("APPSECRET",
					config.getAppSecret());

			HttpGet getMethod = HttpClientUtil.getGetMethod(requestUrl);
			response = instance.execute(getMethod);
			String string = EntityUtils.toString(response.getEntity(), "UTF-8");
			getMethod.releaseConnection();
			return JSON.parseObject(string, AccessTokenModel.class);
		} catch (Exception e) {
			throw new MutilsErrorException(e, "小程序获取AccessToken失败");
		} finally {
			IOUtil.close(instance, response);
		}
	}

	/**
	 * 获取小程序码
	 * 参考 https://developers.weixin.qq.com/miniprogram/dev/api/getWXACodeUnlimit.html
	 * @param model
	 * @return
	 * @throws MutilsErrorException
	 */
	public static InputStream getMiniProgramQrCode(MiniProgramCodeModel model) throws MutilsErrorException {
		model.verificationField();
		CloseableHttpClient httpClient = HttpClientUtil.getInstance();
		CloseableHttpResponse response =null;
		try {
			HttpPost httpPost = HttpClientUtil.getPostMethod("https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=" + model.getAccess_token()); // 接口
			httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
			StringEntity entity = new StringEntity(model.toString());
			entity.setContentType("image/png");
			httpPost.setEntity(entity);
			 response = httpClient.execute(httpPost);
			return response.getEntity().getContent();
		} catch (Exception e) {
			throw new MutilsErrorException(e, "获取小程序码二维码失败");
		}finally {
			IOUtil.close(httpClient,response);
		}

	}
}
