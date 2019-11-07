package cn.minsin.core.web.result;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * service层 与controller层传输对象
 *
 * @author: minton.zhang
 * @since: 0.0.8.RELEASE
 */
public class TransferResult<T> extends BaseResult<TransferResult> {

    @Setter
    @Getter
    @Accessors(chain = true)
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
    public TransferResult(ResultOptions baseResult) {
        this.msg = baseResult.getMsg();
        this.code = baseResult.getCode();
    }

    /**
     * 创建Result对象
     *
     * @param type
     * @return
     */
    public Result builderResult(String type) {
        boolean success = this.isSuccess();
        return Result.builderOptionalResult(success, type);
    }

}
