package cn.minsin.dianwoda.model;

import java.math.BigDecimal;

import cn.minsin.core.annotation.NotNull;
import cn.minsin.core.rule.AbstractModelRule;
import cn.minsin.core.tools.NumberUtil;

/**
 * 点我达下单时所需的模板 需要初始化它
 * 
 * @author minsin
 *
 */
public class OrderModel extends AbstractModelRule {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3686415269847323040L;
	@NotNull("渠道订单编号")
	private String order_original_id;
	@NotNull("渠道订单创建时间戳，默认当前秒数时间")
	private Long order_create_time = System.currentTimeMillis() / 1000;
	@NotNull("订单备注")
	private String order_remark;
	@NotNull("订单金额(分) 必须大于0")
	private BigDecimal order_price;
	@NotNull("订单商品重量，单位：克，若无则传0")
	private Integer cargo_weight = 0;
	@NotNull("商品份数，默认传1")
	private Integer cargo_num = 1;
	@NotNull("行政区划代码，如杭州：330100  参照 cn.minsin.dianwoda.code.CityCode")
	private String city_code;
	@NotNull("商家编号")
	private String seller_id;
	@NotNull("商家店铺名称")
	private String seller_name;
	@NotNull("商家联系方式")
	private String seller_mobile;
	@NotNull("商家文字地址")
	private String seller_address;
	@NotNull("商家纬度坐标.(坐标系为高德地图坐标系，又称火星坐标). （单位：度）")
	private Double seller_lat;
	@NotNull("商家经度坐标.(坐标系为高德地图坐标系，又称火星坐标) （单位：度）")
	private Double seller_lng;
	@NotNull("收货人姓名")
	private String consignee_name;
	@NotNull("收货人手机号码")
	private String consignee_mobile;
	@NotNull("收货人地址")
	private String consignee_address;
	@NotNull("收货人纬度坐标.(坐标系为高德地图坐标系，又称火星坐标)")
	private Double consignee_lat;
	@NotNull("收货人经度坐标.(坐标系为高德地图坐标系，又称火星坐标)")
	private Double consignee_lng;
	@NotNull("配送员到店是否需要垫付订单金额（1：是 0：否)")
	private Integer money_rider_needpaid = 0;
	@NotNull("配送员到店后先行垫付的金额(分)，一般货到付款情况下等于餐品价格。若无，传0")
	private Integer money_rider_prepaid = 0;
	@NotNull("配送员送达到客户时，向客户收取的费用（分）")
	private Integer money_rider_charge = 0;
	@NotNull("商品必须到店才开始准备或是排队购买的情况下，在商家处等待所需时间（秒）")
	private Integer time_waiting_at_seller;
	@NotNull("渠道支付配送费(分)")
	private Integer delivery_fee_from_seller;

	public String getOrder_original_id() {
		return order_original_id;
	}

	public void setOrder_original_id(String order_original_id) {
		this.order_original_id = order_original_id;
	}

	public Long getOrder_create_time() {
		return order_create_time;
	}

	public void setOrder_create_time(Long order_create_time) {
		this.order_create_time = order_create_time;
	}

	public String getOrder_remark() {
		return order_remark;
	}

	public void setOrder_remark(String order_remark) {
		this.order_remark = order_remark;
	}

	public BigDecimal getOrder_price() {
		return order_price;
	}

	public void setOrder_price(BigDecimal order_price) {
		if(order_price!=null) {
			order_price = NumberUtil.yuanToFen(order_price);
		}
		this.order_price = order_price;
	}

	public Integer getCargo_weight() {
		return cargo_weight;
	}

	public void setCargo_weight(Integer cargo_weight) {
		this.cargo_weight = cargo_weight;
	}

	public Integer getCargo_num() {
		return cargo_num;
	}

	public void setCargo_num(Integer cargo_num) {
		this.cargo_num = cargo_num;
	}

	public String getCity_code() {
		return city_code;
	}

	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}

	public String getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}

	public String getSeller_name() {
		return seller_name;
	}

	public void setSeller_name(String seller_name) {
		this.seller_name = seller_name;
	}

	public String getSeller_mobile() {
		return seller_mobile;
	}

	public void setSeller_mobile(String seller_mobile) {
		this.seller_mobile = seller_mobile;
	}

	public String getSeller_address() {
		return seller_address;
	}

	public void setSeller_address(String seller_address) {
		this.seller_address = seller_address;
	}

	public Double getSeller_lat() {
		return seller_lat;
	}

	public void setSeller_lat(Double seller_lat) {
		this.seller_lat = seller_lat;
	}

	public Double getSeller_lng() {
		return seller_lng;
	}

	public void setSeller_lng(Double seller_lng) {
		this.seller_lng = seller_lng;
	}

	public String getConsignee_name() {
		return consignee_name;
	}

	public void setConsignee_name(String consignee_name) {
		this.consignee_name = consignee_name;
	}

	public String getConsignee_mobile() {
		return consignee_mobile;
	}

	public void setConsignee_mobile(String consignee_mobile) {
		this.consignee_mobile = consignee_mobile;
	}

	public String getConsignee_address() {
		return consignee_address;
	}

	public void setConsignee_address(String consignee_address) {
		this.consignee_address = consignee_address;
	}

	public Double getConsignee_lat() {
		return consignee_lat;
	}

	public void setConsignee_lat(Double consignee_lat) {
		this.consignee_lat = consignee_lat;
	}

	public Double getConsignee_lng() {
		return consignee_lng;
	}

	public void setConsignee_lng(Double consignee_lng) {
		this.consignee_lng = consignee_lng;
	}

	public Integer getMoney_rider_needpaid() {
		return money_rider_needpaid;
	}

	public void setMoney_rider_needpaid(Integer money_rider_needpaid) {
		this.money_rider_needpaid = money_rider_needpaid;
	}

	public Integer getMoney_rider_prepaid() {
		return money_rider_prepaid;
	}

	public void setMoney_rider_prepaid(Integer money_rider_prepaid) {
		this.money_rider_prepaid = money_rider_prepaid;
	}

	public Integer getMoney_rider_charge() {
		return money_rider_charge;
	}

	public void setMoney_rider_charge(Integer money_rider_charge) {
		this.money_rider_charge = money_rider_charge;
	}

	public Integer getTime_waiting_at_seller() {
		return time_waiting_at_seller;
	}

	public void setTime_waiting_at_seller(Integer time_waiting_at_seller) {
		this.time_waiting_at_seller = time_waiting_at_seller;
	}

	public Integer getDelivery_fee_from_seller() {
		return delivery_fee_from_seller;
	}

	public void setDelivery_fee_from_seller(Integer delivery_fee_from_seller) {
		this.delivery_fee_from_seller = delivery_fee_from_seller;
	}
}
