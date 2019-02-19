package cn.mutils.meituan.peisong.enums;

/**
 * 取消原因id枚举
 */
public enum CancelOrderReason {
	/**
	 * 	接入方其他原因
	 */
    PARTNER_REASON(199, "其他接入方原因"), 
    /**
     * 	 配送方其他原因
     */
    DELIVER_REASON(299, "其他美团配送原因"),
    /**
     * 	其他原因
     */
    OTHER(399, "其他原因"); 

    private int code;
    private String msg;

    CancelOrderReason(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static CancelOrderReason findByCode(int code) {
        for (CancelOrderReason type : CancelOrderReason.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
