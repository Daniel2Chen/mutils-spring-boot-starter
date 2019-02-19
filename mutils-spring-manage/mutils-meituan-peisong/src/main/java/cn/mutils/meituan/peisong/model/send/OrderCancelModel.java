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
	private CancelOrderReason cancelOrderReasonId =CancelOrderReason.DELIVER_REASON;
	
	@NotNull(key="cancel_reason",value="详细取消原因，最长不超过256个字符")
	private String cancelReason;

	public long getDeliveryId() {
		return deliveryId;
	}

	public void setDeliveryId(long deliveryId) {
		this.deliveryId = deliveryId;
	}

	public String getMtPeisongId() {
		return mtPeisongId;
	}

	public void setMtPeisongId(String mtPeisongId) {
		this.mtPeisongId = mtPeisongId;
	}

	public CancelOrderReason getCancelOrderReasonId() {
		return cancelOrderReasonId;
	}

	public void setCancelOrderReasonId(CancelOrderReason cancelOrderReasonId) {
		this.cancelOrderReasonId = cancelOrderReasonId;
	}

	public String getCancelReason() {
		return cancelReason;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	@Override
	public String toString() {
		return "CancelOrderRequest [deliveryId=" + deliveryId + ", mtPeisongId=" + mtPeisongId
				+ ", cancelOrderReasonId=" + cancelOrderReasonId + ", cancelReason=" + cancelReason + "]";
	}
}
