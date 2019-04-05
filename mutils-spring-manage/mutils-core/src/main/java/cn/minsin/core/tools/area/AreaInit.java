package cn.minsin.core.tools.area;

/**
 * 	获取地址信息的
 * @author mintonzhang
 * @date 2019年4月5日
 * @since 0.2.
 */
public class AreaInit {
	
	private String remoteUrl="http://datavmap-public.oss-cn-hangzhou.aliyuncs.com/areas/csv/";
	
	private String defaultProvinceCode ="100000";
	
	private String province="${code}_province.json";
	
	private String city ="${code}_city.json";
	
	private String district="${code}_district.json";

	private String placeholder="${code}";
	
	
	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public String getDefaultProvinceCode() {
		return defaultProvinceCode;
	}

	public void setDefaultProvinceCode(String defaultProvinceCode) {
		this.defaultProvinceCode = defaultProvinceCode;
	}

	public String getRemoteUrl() {
		return remoteUrl;
	}

	public void setRemoteUrl(String remoteUrl) {
		this.remoteUrl = remoteUrl;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

}
