package cn.minsin.core.tools.area;

import java.util.List;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.minsin.core.exception.MutilsErrorException;
import cn.minsin.core.tools.HttpClientUtil;
import cn.minsin.core.tools.IOUtil;

/**
 * 中国地域信息 浙江省、四川省等等
 * 
 * @author mintonzhang
 * @date 2019年4月5日
 * @since 0.3.7
 */
public class ChinaAreaUtil {
	protected static Logger slog = LoggerFactory.getLogger(ChinaAreaUtil.class);
	
	private AreaInit init;

	private CloseableHttpClient client;

	private ChinaAreaUtil(AreaInit init) {
		this.init = init == null ? new AreaInit() : init;
		this.client = HttpClientUtil.getInstance();
	}

	public static ChinaAreaUtil init() {
		return new ChinaAreaUtil(null);
	}

	/**
	 * 获取省列表
	 * 
	 * @return
	 * @throws MutilsErrorException
	 */
	public List<AreaModel> initProvince() throws MutilsErrorException {
		CloseableHttpResponse response = null;
		try {
			HttpGet getMethod = HttpClientUtil.getGetMethod(init.getRemoteUrl()
					+ init.getProvince().replace(init.getPlaceholder(), init.getDefaultProvinceCode()));
			response = client.execute(getMethod);
			String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
			JSONObject parseObject = JSON.parseObject(jsonStr);
			Object object = parseObject.get("rows");
			return JSON.parseArray(object.toString(), AreaModel.class);
		} catch (Exception e) {
			throw new MutilsErrorException(e);
		} finally {
			IOUtil.close(response);
		}
	}

	/**
	 * 获取城市
	 * 
	 * @param provinceCode
	 * @return
	 * @throws MutilsErrorException
	 */
	public List<AreaModel> initCity(String provinceCode) throws MutilsErrorException {
		CloseableHttpResponse response = null;
		try {
			HttpGet getMethod = HttpClientUtil
					.getGetMethod(init.getRemoteUrl() + init.getCity().replace(init.getPlaceholder(), provinceCode));
			response = client.execute(getMethod);
			String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
			JSONObject parseObject = JSON.parseObject(jsonStr);
			Object object = parseObject.get("rows");
			return JSON.parseArray(object.toString(), AreaModel.class);
		} catch (Exception e) {
			throw new MutilsErrorException(e);
		} finally {
			IOUtil.close(response);
		}
	}

	/**
	 *	获取县市区
	 * @param cityCode
	 * @return
	 * @throws MutilsErrorException
	 */
	public List<AreaModel> initDistrict(String cityCode) throws MutilsErrorException {
		CloseableHttpResponse response = null;
		try {
			HttpGet getMethod = HttpClientUtil
					.getGetMethod(init.getRemoteUrl() + init.getDistrict().replace(init.getPlaceholder(), cityCode));
			response = client.execute(getMethod);
			String jsonStr = EntityUtils.toString(response.getEntity(), "UTF-8");
			JSONObject parseObject = JSON.parseObject(jsonStr);
			Object object = parseObject.get("rows");
			return JSON.parseArray(object.toString(), AreaModel.class);
		} catch (Exception e) {
			throw new MutilsErrorException(e);
		} finally {
			IOUtil.close(response);
		}
	}

	/**
	 * 获取省市区的树状图，由于数据量大 可用于重设本地数据库
	 * 
	 * @return
	 * @throws MutilsErrorException
	 */
	public List<AreaModel> initProvinceWithChildren() throws MutilsErrorException {
		List<AreaModel> initProvince = initProvince();
		for (AreaModel province : initProvince) {

			List<AreaModel> initCity = this.initCity(province.getAdcode());
			for (AreaModel city : initCity) {
				try {
					List<AreaModel> initDistrict = this.initDistrict(city.getAdcode());
					city.setChildren(initDistrict);
				} catch (Exception e) {
					slog.error("adcode为：{},name为{}，丢失数据,已跳过.", city.getAdcode(), city.getName());
				}
			}
			province.setChildren(initCity);
		}
		return initProvince;
	}

}
