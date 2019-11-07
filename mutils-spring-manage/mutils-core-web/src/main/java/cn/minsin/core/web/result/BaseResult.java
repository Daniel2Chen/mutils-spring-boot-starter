package cn.minsin.core.web.result;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    protected BaseResult() {
    }

    protected BaseResult(ResultOptions options, String... msg) {
        String rmsg = options.getMsg();
        if (msg != null && msg.length > 0) {
            rmsg = msg[0];
        }
        this.code = options.getCode();
        this.msg = rmsg;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }


    @JsonIgnore
    public T setResult(ResultOptions options) {
        this.code = options.getCode();
        this.msg = options.getMsg();
        return (T) this;
    }

    @JsonIgnore
    public T setResult(BaseResult baseResult) {
        this.code = baseResult.code;
        this.msg = baseResult.msg;
        return (T) this;
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

    protected T setCode(int code) {
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
