package cn.minsin.yiketong.model;

import cn.minsin.core.rule.AbstractModelRule;
import cn.minsin.core.tools.StringUtil;

/**
 * 号码转换返回值
 * @author mintonzhang
 * @date 2019年2月26日
 * @since 0.3.6
 */
public class ResultDetailModel  extends AbstractModelRule{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6404910704747689921L;

	private String message;

	private String tel_x;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTel_x() {
		return tel_x;
	}

	public void setTel_x(String tel_x) {
		this.tel_x = tel_x;
	}

	public boolean isSuccess() {
		return StringUtil.isNotBlank(tel_x);
	}
}
