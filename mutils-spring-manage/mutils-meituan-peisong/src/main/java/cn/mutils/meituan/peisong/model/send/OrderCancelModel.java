package cn.mutils.meituan.peisong.model.send;

import cn.minsin.core.annotation.NotNull;
import cn.mutils.meituan.peisong.enums.CancelOrderReason;

/**
 * 取消订单参数
 */
public class OrderCancelModel extends AbstractMeituanSendModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6983058190233718060L;

	@NotNull(key="delivery_id",value="配送活动标识")
	private Long deliveryId;

	@NotNull(key="mt_peisong_id",value="美团配送内部订单id，最长不超过32个字符")
	private String mtPeisongId;

	@NotNull(key="cancel_reason_id",value="取消原因类别，默认为接入方原因")
	private int cancelOrderReasonId =CancelOrderReason.DELIVER_REASON.getCode();
	
	@NotNull(key="cancel_reason",value="详细取消原因，最长不超过256个字符")
	private String cancelReason;

	public Long getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(Long deliveryId) {
		this.deliveryId = deliveryId;
	}

	public String getMtPeisongId() {
		return mtPeisongId;
	}

	public void setMtPeisongId(String mtPeisongId) {
		this.mtPeisongId = mtPeisongId;
	}

	public int getCancelOrderReasonId() {
		return cancelOrderReasonId;
	}

	public void setCancelOrderReasonId(int cancelOrderReasonId) {
		this.cancelOrderReasonId = cancelOrderReasonId;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}
}
