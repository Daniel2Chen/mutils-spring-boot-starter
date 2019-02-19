package cn.mutils.meituan.peisong.model.receive;

public class ReceiveInfoModel extends AbstractMeituanReceiveModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7045557269744515805L;

	
    private OrderInfoModel data;


	public OrderInfoModel getData() {
		return data;
	}
	public void setData(OrderInfoModel data) {
		this.data = data;
	}
}
