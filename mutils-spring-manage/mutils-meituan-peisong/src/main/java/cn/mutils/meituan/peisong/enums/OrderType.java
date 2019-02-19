package cn.mutils.meituan.peisong.enums;

/**
 * 订单类型
 */
public enum OrderType {
	/**
	 *  及时单
	 */
    NORMAL(0),
    /**
     * 预约单
     */
    PREBOOK(1); 

    private final int code;

     OrderType(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
