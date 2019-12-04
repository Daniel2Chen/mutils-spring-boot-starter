package cn.minsin.core.web.result;

import com.alibaba.fastjson.JSON;
import lombok.Getter;

import java.io.Serializable;

/**
 * @author: minton.zhang
 * @since: 0.0.8.RELEASE
 */

@SuppressWarnings("unchecked")
public abstract class BaseResult<T> implements Serializable {

    @Getter
    protected int code;
    @Getter
    protected String msg;

    public BaseResult() {
    }

    public BaseResult(ResultOptions options, String msg) {
        this.code = options.getCode();
        this.msg = msg;
    }

    public BaseResult(BaseResult baseResult) {
        this.code = baseResult.getCode();
        this.msg = baseResult.getMsg();
    }

    public BaseResult(ResultOptions resultOptions) {
        this.code = resultOptions.getCode();
        this.msg = resultOptions.getMsg();
    }

    public BaseResult(boolean isSuccess) {
        DefaultResultOptions defaultResultOptions = isSuccess ? DefaultResultOptions.SUCCESS : DefaultResultOptions.FAIL;
        this.code = defaultResultOptions.getCode();
        this.msg = defaultResultOptions.getMsg();
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }


    public T appendMsg(boolean condition, String msg) {
        if (this.isSuccess() == condition) {
            this.msg = this.msg.concat(",").concat(msg);
        }
        return (T) this;
    }

    public T setMsg(String message) {
        this.msg = message;
        return (T) this;
    }

    public T setCode(int code) {
        this.code = code;
        return (T) this;
    }


    public boolean isSuccess() {
        return this.code == DefaultResultOptions.SUCCESS.getCode();
    }

    public boolean isFail() {
        return !this.isSuccess();
    }

}
