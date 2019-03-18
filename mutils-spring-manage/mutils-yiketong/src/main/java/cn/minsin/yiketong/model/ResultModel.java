package cn.minsin.yiketong.model;

import cn.minsin.core.rule.AbstractModelRule;

/**
 * 返回结果集解析
 * 
 * @author mintonzhang
 * @date 2019年1月10日
 */
public class ResultModel extends AbstractModelRule {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1238311443023476513L;

	private int code;

	private String message;

	private ResultDetailModel data;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResultDetailModel getData() {
		return data;
	}

	public void setData(ResultDetailModel data) {
		this.data = data;
	}

	public boolean isSuccess() {
		if (data == null) {
			return false;
		}
		return data.isSuccess();
	}
}
