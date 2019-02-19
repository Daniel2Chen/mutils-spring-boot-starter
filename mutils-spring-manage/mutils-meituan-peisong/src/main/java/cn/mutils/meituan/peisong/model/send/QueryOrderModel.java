package cn.mutils.meituan.peisong.model.send;

import cn.minsin.core.annotation.NotNull;

/**
 * 查询订单参数
 */
public class QueryOrderModel extends AbstractMeituanSendModel {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3473963293793612484L;

    @NotNull(key="delivery_id",value="配送活动标识")
    private Long deliveryId;

    @NotNull(key="mt_peisong_id",value="美团配送内部订单id，最长不超过32个字符")
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
