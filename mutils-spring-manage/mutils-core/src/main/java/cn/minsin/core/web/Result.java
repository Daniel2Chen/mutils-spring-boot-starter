/**
 * 
 */
package cn.minsin.core.web;

import java.io.Serializable;
import java.util.HashMap;

import com.alibaba.fastjson.JSON;

import cn.minsin.core.exception.MutilsException;

/**
 * 	构建者模式的Result 
 * Eg：Result.builderMissParamter().data('name',"张三")
 * @author mintonzhang
 * @since 0.1.0
 */
public class Result implements Serializable {

	private static final long serialVersionUID = -8603085056620027210L;

	protected Result() {
	}

	protected Result(ResultOptions options, String... msg) {
		validateOption(options);
		String rmsg = options.getMsg();
		if (msg != null && msg.length > 0) {
			rmsg = msg[0];
		}
		this.code = options.getCode();
		this.msg = rmsg;
	}

	private int code;

	private String msg;

	private Object data;

	private HashMap<String, Object> multidata;

	public HashMap<String, Object> getMultidata() {
		return multidata;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public Object getData() {
		return data;
	}

	/**
	 * 重置提示语和code
	 * 
	 * @param option
	 * @return
	 */
	public Result resetOption(ResultOptions option, String... msg) {
		validateOption(option);
		String rmsg = option.getMsg();
		if (msg != null && msg.length > 0) {
			rmsg = msg[0];
		}
		this.code = option.getCode();
		this.msg = rmsg;
		return this;
	}

	public Result data(String key, Object value) {
		if (multidata == null) {
			multidata = new HashMap<>(10);
		}
		multidata.put(key, value);
		return this;
	}

	public Result data(Object value) {
		data = value;
		return this;
	}

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}

	/**
	 * 构造result
	 * 
	 * @param option  需要实现接口ResultOptions 的枚举 默认枚举是 DefaultResultOptions
	 * @param message 提示给前端的消息
	 * @return
	 */
	public static Result builder(ResultOptions option, String... message) {
		return new Result(option, message);
	}

	/**
	 * code 来自Result中的 SUCCESS 或 EXCEPTION
	 * 
	 * @param message this default is '操作成功'
	 */
	public static Result builderSuccess(String... msg) {
		return new Result(DefaultResultOptions.SUCCESS, msg);
	}

	/**
	 * code 来自Result中的 SUCCESS 或 EXCEPTION
	 * 
	 * @param message this default is '服务器异常'
	 */
	public static Result builderException(String... msg) {
		return new Result(DefaultResultOptions.EXCEPTION, msg);
	}

	/**
	 * 构建缺少参数的Result
	 * 
	 * @param message this default is '缺少必要参数'
	 */
	public static Result builderMissParamter(String... msg) {
		return new Result(DefaultResultOptions.MISSPARAMTER, msg);
	}

	/**
	 * 构建失败消息
	 * 
	 * @param message this default is '操作失败'
	 */
	public static Result builderFail(String... msg) {
		return new Result(DefaultResultOptions.FAIL, msg);
	}

	/**
	 * 构建用户过期
	 * 
	 * @param message this default is '用户已失效'
	 */
	public static Result builderOutTime(String... msg) {
		return new Result(DefaultResultOptions.OUTTIME, msg);
	}
	/**
	 * 构建错误
	 * 
	 * @param message this default is '服务器跑路了，请稍后重试'
	 */
	public static Result builderError(String... msg) {
		return new Result(DefaultResultOptions.ERROR, msg);
	}

	private void validateOption(ResultOptions option) {
		if (option == null || !option.getClass().isEnum()) {
			throw new MutilsException("ResultOptions must be an enumeration and implement ResultOptions.");
		}
	}
}