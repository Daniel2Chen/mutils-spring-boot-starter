package cn.mutils.meituan.peisong.enums;

/**
 * 订单状态
 */
public enum OrderStatus {
    CREATED(0, "待调度"),
    ACCEPTED(20, "已接单"),
    FETCHED(30, "已取货"),
    DELIVERED(50, "已送达"),
    CANCELED(99, "已取消");

    private int code;
    private String description;

    private OrderStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public static OrderStatus findByCode(int code) {
        for (OrderStatus type : OrderStatus.values()) {
            if (type.getCode() == code) {
                return type;
            }
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
