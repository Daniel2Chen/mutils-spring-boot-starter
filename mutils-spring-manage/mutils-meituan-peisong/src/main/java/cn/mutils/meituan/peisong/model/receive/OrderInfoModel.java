package cn.mutils.meituan.peisong.model.receive;

import cn.minsin.core.rule.AbstractModelRule;

public class OrderInfoModel extends AbstractModelRule {
	/**
	 * 
	 */
	private static final long serialVersionUID = 854194625732274453L;
	/**
	 * 配送唯一标识
	 */
	private String mt_peisong_id;
	/**
	 * 订单ID
	 */
	private String order_id;
	/**
	 * 配送活动标识
	 */
	private Long delivery_id;
	/**
	 * 目的地id
	 */
	private String destination_id;
	/**
	 * 订单配送距离
	 */
	private Integer delivery_distance;
	/**
	 * 订单配送价格（面向商家）
	 */
	private Double delivery_fee;
	/**
	 * 路区信息
	 */
	private String road_area;

	public Long getDelivery_id() {
		return delivery_id;
	}

	public void setDelivery_id(Long delivery_id) {
		this.delivery_id = delivery_id;
	}

	public String getMt_peisong_id() {
		return mt_peisong_id;
	}

	public void setMt_peisong_id(String mt_peisong_id) {
		this.mt_peisong_id = mt_peisong_id;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getDestination_id() {
		return destination_id;
	}

	public void setDestination_id(String destination_id) {
		this.destination_id = destination_id;
	}

	public Integer getDelivery_distance() {
		if(delivery_distance==null||delivery_distance==-1) {
			return 0;
		}
		return delivery_distance;
	}

	public void setDelivery_distance(Integer delivery_distance) {
		this.delivery_distance = delivery_distance;
	}

	public Double getDelivery_fee() {
		if(delivery_fee==null||delivery_fee==-1) {
			return 0D;
		}
		return delivery_fee;
	}

	public void setDelivery_fee(Double delivery_fee) {
		this.delivery_fee = delivery_fee;
	}

	public String getRoad_area() {
		return road_area;
	}

	public void setRoad_area(String road_area) {
		this.road_area = road_area;
	}
}
