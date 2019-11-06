package cn.minsin.meituan.peisong.model.send;

import cn.minsin.core.annotation.NotNull;

/**
 * 	根据合作方提供的模拟发单参数，确定美团是否可配送。主要校验项：门店是否存在、门店配送范围、门店营业时间、门店支持的服务包。
 * @author mintonzhang
 * @date 2019年2月19日
 * @since 0.3.4
 */
public class CheckShopModel extends AbstractMeituanSendModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5592686221604015896L;
	
	@NotNull(key="shop_id",value="取货门店 id，即合作方向美团提供的门店 id。注：测试门店的 shop_id 固定为 test_0001，仅用于对接时联调测试。")
	private String shopId;
	
	@NotNull(key="delivery_service_code",value="配送服务代码，详情见合同 飞速达: 4002 快速达: 4011 及时达: 4012 集中送: 4013")
	private Integer deliveryServiceCode;
	
	@NotNull(key="receiver_address",value="收件人地址，最长不超过 512 个字符")
	private String receiverAddress;
	
	@NotNull(key="receiver_lng",value="收件人经度（高德坐标），高德坐标 *（ 10 的六次方），如 116398419")
	private Integer receiverLng;
	
	@NotNull(key="receiver_lat",value=" 收件人纬度（高德坐标），高德坐标 *（ 10 的六次方），如 39985005")
	private Integer receiverLat;
	
	@NotNull(key="check_type",
			value="预留字段，方便以后扩充校验规则，check_type = 1")
	private Integer checkType =1;
	
	@NotNull(key="mock_order_time",value="模拟发单时间，时区为 GMT+8，当前距离 Epoch（1970年1月1日) 以秒计算的时间，即 unix-timestamp。")
	private Long mockOrderTime;
	
	@NotNull(key="coordinate_type",value="坐标类型，0：火星坐标（高德，腾讯地图均采用火星坐标） 1：百度坐标 （默认值为0）")
	private Integer coordinateType =0;

	public String getShopId() {
		return shopId;
	}

	public void setShopId(String shopId) {
		this.shopId = shopId;
	}

	public Integer getDeliveryServiceCode() {
		return deliveryServiceCode;
	}

	public void setDeliveryServiceCode(Integer deliveryServiceCode) {
		this.deliveryServiceCode = deliveryServiceCode;
	}

	public String getReceiverAddress() {
		return receiverAddress;
	}

	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}

	public Integer getReceiverLng() {
		return receiverLng;
	}

	public void setReceiverLng(Integer receiverLng) {
		this.receiverLng = receiverLng;
	}

	public Integer getReceiverLat() {
		return receiverLat;
	}

	public void setReceiverLat(Integer receiverLat) {
		this.receiverLat = receiverLat;
	}

	public Integer getCheckType() {
		return checkType;
	}

	public void setCheckType(Integer checkType) {
		this.checkType = checkType;
	}

	public Long getMockOrderTime() {
		return mockOrderTime;
	}

	public void setMockOrderTime(Long mockOrderTime) {
		this.mockOrderTime = mockOrderTime;
	}
}
