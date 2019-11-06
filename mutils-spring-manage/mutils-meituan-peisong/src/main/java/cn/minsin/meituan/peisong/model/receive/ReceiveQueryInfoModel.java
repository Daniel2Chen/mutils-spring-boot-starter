package cn.minsin.meituan.peisong.model.receive;

public class ReceiveQueryInfoModel extends AbstractMeituanReceiveModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7045557269744515805L;

	
    private OrderStatusInfoModel data;


	public OrderStatusInfoModel getData() {
		return data;
	}


	public void setData(OrderStatusInfoModel data) {
		this.data = data;
	}
}
