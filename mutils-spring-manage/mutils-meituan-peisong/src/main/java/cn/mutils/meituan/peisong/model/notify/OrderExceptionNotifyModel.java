package cn.mutils.meituan.peisong.model.notify;

import cn.mutils.meituan.peisong.enums.ExceptionType;

/**
 * 回调根据http响应码为200且返回{"code":0} 判断为成功，否则为失败 若第一次回调失败，会在10分钟内重试5次，且每次重试时间间隔逐步延长
 * 若5次重试全部失败，会在之后每小时重试一次，直到当天结束。
 * 
 * @author mintonzhang
 * @date 2019年2月19日
 * @since 0.3.4
 */
public class OrderExceptionNotifyModel extends AbstractMeituanNotifyModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6292889508036659386L;

	/**
	 * 配送活动标识
	 */
	private Long delivery_id;

	/**
	 * 美团配送内部订单id，最长不超过32个字符
	 */
	private String mt_peisong_id;

	/**
	 * 外部订单号，最长不超过32个字符
	 */
	private String order_id;

	/**
	 * 异常ID，用来唯一标识一个订单异常信息。接入方用此字段用保证接口调用的幂等性。
	 */
	private Long exception_id;

	/**
	 * 订单异常代码，当前可能的值为：{@link ExceptionType}
	 */
	private ExceptionType exception_code;

	/**
	 * 订单异常详细信息
	 * 
	 */
	private String exception_descr;

	/**
	 * 配送员上报订单异常的时间，格式为long，时区为GMT+8，距离Epoch(1970年1月1日) 以秒计算的时间，即unix-timestamp。
	 */
	private Long exception_time;

	/**
	 * 上报订单异常的配送员姓名
	 */
	private String courier_name;

	/**
	 * 上报订单异常的配送员电话
	 */
	private String courier_phone;

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

	public Long getException_id() {
		return exception_id;
	}

	public void setException_id(Long exception_id) {
		this.exception_id = exception_id;
	}

	public ExceptionType getException_code() {
		return exception_code;
	}

	public void setException_code(int exception_code) {
		this.exception_code = ExceptionType.findByCode(exception_code);
	}

	public String getException_descr() {
		return exception_descr;
	}

	public void setException_descr(String exception_descr) {
		this.exception_descr = exception_descr;
	}

	public Long getException_time() {
		return exception_time;
	}

	public void setException_time(Long exception_time) {
		this.exception_time = exception_time;
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

}
