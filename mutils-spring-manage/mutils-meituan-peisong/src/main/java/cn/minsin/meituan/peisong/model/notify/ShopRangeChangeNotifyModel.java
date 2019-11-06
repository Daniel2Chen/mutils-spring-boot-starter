package cn.minsin.meituan.peisong.model.notify;

public class ShopRangeChangeNotifyModel extends AbstractMeituanNotifyModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8375223185352636803L;

	/**
	 * 	门店配送范围
	 */
	private String scope;
	
	/**
	 * 	取货门店id
	 */
	private String shop_id;
	
	/**
	 * 	配送服务代码
	 */
	private Integer delivery_service_code;

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getShop_id() {
		return shop_id;
	}

	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}

	public Integer getDelivery_service_code() {
		return delivery_service_code;
	}

	public void setDelivery_service_code(Integer delivery_service_code) {
		this.delivery_service_code = delivery_service_code;
	}
	
	
}
