package cn.minsin.core.web;

import com.alibaba.fastjson.JSON;
import lombok.Getter;

import java.io.Serializable;

/**
 * @author: minton.zhang
 * @since: 2019/10/28 10:11
 */
public abstract class BaseResult<T> implements Serializable {

    @Getter
    protected int code;

    @Getter
    protected String msg;

    protected transient ResultOptions options;

    protected BaseResult() {
    }

    protected BaseResult(ResultOptions options, String... msg) {
        String rmsg = options.getMsg();
        if (msg != null && msg.length > 0) {
            rmsg = msg[0];
        }
        this.options = options;
        this.code = options.getCode();
        this.msg = rmsg;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    public boolean isSuccess() {
        return this.code == DefaultResultOptions.SUCCESS.getCode();
    }


    public T appendMsg(boolean condition, String msg) {
        if (isSuccess() == condition) {
            this.msg = this.msg.concat(",").concat(msg);
        }
        return (T) this;
    }
}
