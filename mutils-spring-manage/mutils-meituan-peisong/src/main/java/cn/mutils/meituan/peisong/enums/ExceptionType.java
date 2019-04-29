package cn.mutils.meituan.peisong.enums;

/**
 * 异常对照表
 * 
 * @author mintonzhang
 * @date 2019年2月19日
 * @since 0.3.4
 */
public enum ExceptionType {

	E10001(10001, "顾客电话关机"),

	E10002(10002, "顾客电话已停机"),

	E10003(10003, "顾客电话无人接听"),

	E10004(10004, "顾客电话为空号"),

	E10005(10005, "顾客留错电话"),

	E10006(10006, "联系不上顾客其他原因"),

	E10101(10101, "顾客更改收货地址"),

	E10201(10201, "送货地址超区"),

	E10202(10202, "顾客拒收货品"),

	E10203(10203, "顾客要求延迟配送"),

	E10401(10401, "商家关店/未营业");

	private int code;
	private String msg;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	private ExceptionType(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public static ExceptionType findByCode(int code) {
		for (ExceptionType type : ExceptionType.values()) {
			if (type.getCode() == code) {
				return type;
			}
		}
		return null;
	}
}
