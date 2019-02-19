package cn.minsin.core.web;

/**
 * web返回对象必须实现ResultOptions
 * @author mintonzhang
 */
public enum DefaultResultOptions implements ResultOptions {
	/**
	 * 服务器出现不可逆错误
	 */
	ERROR(500, "服务器跑路了，请稍后重试"),
	/**
	 * 服务器发生异常
	 */
	EXCEPTION(404, "系统开小差了，请稍后重试"),
	/**
	 * 操作成功
	 */
	SUCCESS(200, "操作成功"), 
	/**
	 * 操作失败
	 */
	FAIL(201, "操作失败"),
	/**
	 * 用户信息失效
	 */
	OUTTIME(202, "您的身份过期了，请重新登录"),
	/**
	 * 缺少参数
	 */
	MISSPARAMTER(203, "您好像少提交了参数");

	/**
	 * 	状态码
	 */
	private int code;

	/**
	 * 	提示消息
	 */
	private String msg;

	@Override
	public int getCode() {
		return code;
	}

	private DefaultResultOptions(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	@Override
	public String getMsg() {
		return msg;
	}
}
