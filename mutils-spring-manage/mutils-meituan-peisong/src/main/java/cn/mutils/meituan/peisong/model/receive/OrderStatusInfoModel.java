package cn.mutils.meituan.peisong.model.receive;

import cn.minsin.core.rule.AbstractModelRule;
import cn.mutils.meituan.peisong.enums.OrderStatus;

/**
 * 订单状态信息
 */
public class OrderStatusInfoModel extends AbstractModelRule {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7955992078653918764L;

	/**
	 * 配送活动标识
	 */
	private String delivery_id;

	/**
	 * 美团配送内部订单id，最长不超过32个字符
	 */
	private String mt_peisong_id;

	/**
	 * 外部订单号，最长不超过32个字符
	 */
	private String order_id;

	/**
	 * 订单状态代码
	 */
	private OrderStatus status;

	/**
	 * 配送员姓名（订单已被骑手接单后会返回骑手信息）
	 */
	private String courier_name;

	/**
	 * 配送员电话（订单已被骑手接单后会返回骑手信息）
	 */
	private String courier_phone;

	/**
	 * 取消原因id
	 */
	private int cancel_reason_id;

	/**
	 * 取消原因详情，最长不超过256个字符
	 */
	private String cancel_reason;

	public String getDelivery_id() {
		return delivery_id;
	}

	public void setDelivery_id(String delivery_id) {
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

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(int code) {
		this.status = OrderStatus.findByCode(code);
	}

	public String getCourier_name() {
		return courier_name;
	}

	public void setCourier_name(String courier_name) {
		this.courier_name = courier_name;
	}

	public String getCourier_phone() {
		return courier_phone;
	}

	public void setCourier_phone(String courier_phone) {
		this.courier_phone = courier_phone;
	}

	public int getCancel_reason_id() {
		return cancel_reason_id;
	}

	public void setCancel_reason_id(int cancel_reason_id) {
		this.cancel_reason_id = cancel_reason_id;
	}

	public String getCancel_reason() {
		return cancel_reason;
	}

	public void setCancel_reason(String cancel_reason) {
		this.cancel_reason = cancel_reason;
	}

	@Override
	public String toString() {
		return "OrderStatusInfo {" + "delivery_id=" + delivery_id + ", mt_peisong_id=" + mt_peisong_id + ", order_id="
				+ order_id + ", status=" + status + ", courier_name=" + courier_name + ", courier_phone="
				+ courier_phone + ", cancel_reason_id=" + cancel_reason_id + ", cancel_reason=" + cancel_reason + '}';
	}
}
