package cn.mutils.meituan.peisong.model.notify;

import cn.mutils.meituan.peisong.enums.CancelOrderReason;
import cn.mutils.meituan.peisong.enums.OrderStatus;

public class OrderStateNotifyModel extends AbstractMeituanNotifyModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 622024090012559726L;

	/**
	 * 	配送活动标识
	 */
	private Long delivery_id;

	/**
	 * 	美团配送内部订单id，最长不超过32个字符
	 */
	private String mt_peisong_id;

	/**
	 * 	外部订单号，最长不超过32个字符
	 */
	private String order_id;

	/**
	 * 	状态代码，可选值为
0：待调度

20：已接单

30：已取货

50：已送达

99：已取消

回调接口的订单状态改变可能会跳过中间状态，比如从待调度状态直接变为已取货状态。

订单状态不会回流。即订单不会从已取货状态回到待调度状态。

订单状态为“已接单”和“已取货”时，如果当前骑手不能完成配送，会出现改派操作，例如：将订单从骑手A改派给骑手B，由骑手B完成后续配送，因此会出现同一订单多次返回同一状态不同骑手信息的情况”
	 */
	private OrderStatus status;

	/**
	 * 	配送员姓名（已接单，已取货状态的订单，配送员信息可能改变）
	 */
	private String courier_name;

	/**
	 * 	配送员电话（已接单，已取货状态的订单，配送员信息可能改变）
	 */
	private String courier_phone;

	/**
	 * 	取消原因id
	 */
	private CancelOrderReason cancel_reason_id;

	/**
	 * 	取消原因详情，最长不超过256个字符
	 */
	private String cancel_reason;

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

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = OrderStatus.findByCode(status);
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

	public CancelOrderReason getCancel_reason_id() {
		return cancel_reason_id;
	}

	public void setCancel_reason_id(int cancel_reason_id) {
		this.cancel_reason_id = CancelOrderReason.findByCode(cancel_reason_id);
	}

	public String getCancel_reason() {
		return cancel_reason;
	}

	public void setCancel_reason(String cancel_reason) {
		this.cancel_reason = cancel_reason;
	}

}
