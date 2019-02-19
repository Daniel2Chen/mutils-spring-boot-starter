package cn.mutils.meituan.peisong.model.send;

import cn.minsin.core.annotation.NotNull;

/**
 * 	模拟订单信息
 * @author mintonzhang
 * @date 2019年2月19日
 * @since 0.3.4
 */
public class OrderMockModel extends AbstractMeituanSendModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4668853614784391927L;

	@NotNull(key = "delivery_id", value = "配送活动标识")
	private Long deliveryId;

	@NotNull(key = "mt_peisong_id", value = "美团配送内部订单id，最长不超过32个字符")
	private String mtPeisongId;

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
}
