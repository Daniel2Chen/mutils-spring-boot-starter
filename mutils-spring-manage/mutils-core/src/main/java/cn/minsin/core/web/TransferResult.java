package cn.minsin.core.web;

import lombok.Getter;
import lombok.Setter;

/**
 * service层 与controller层传输对象
 *
 * @author: minton.zhang
 * @since: 2019/11/5 15:28
 */
public class TransferResult<T> extends BaseResult<T> {

    @Setter
    @Getter
    private T value;

    public TransferResult() {

    }

    public TransferResult(BaseResult baseResult, T data) {
        this.code = baseResult.code;
        this.msg = baseResult.msg;
        this.value = data;
    }

    public TransferResult(BaseResult baseResult) {
        this.msg = baseResult.msg;
        this.code = baseResult.code;
    }

    public TransferResult setResult(BaseResult baseResult) {
        this.code = baseResult.code;
        this.msg = baseResult.msg;
        return this;
    }

    public TransferResult setMsg(String message) {
        this.msg = message;
        return this;
    }

    public TransferResult setCode(int code) {
        this.code = code;
        return this;
    }

    /**
     * 创建Result对象
     *
     * @param type
     * @return
     */
    public Result builderResult(DefaultOperationType type) {
        boolean success = this.isSuccess();
        return Result.builderOptionalResult(success, type);
    }

}
