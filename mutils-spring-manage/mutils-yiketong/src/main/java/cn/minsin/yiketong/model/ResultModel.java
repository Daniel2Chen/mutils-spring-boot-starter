package cn.minsin.yiketong.model;

import cn.minsin.core.rule.AbstractModelRule;
import lombok.Getter;
import lombok.Setter;

/**
 * 返回结果集解析
 * 
 * @author mintonzhang
 * @date 2019年1月10日
 */
@Getter
@Setter
public class ResultModel extends AbstractModelRule {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1238311443023476513L;

	private int code;

	private String message;

	private ResultDetailModel data;

	public boolean isSuccess() {
		if (data == null) {
			return false;
		}
		return data.isSuccess();
	}
}
