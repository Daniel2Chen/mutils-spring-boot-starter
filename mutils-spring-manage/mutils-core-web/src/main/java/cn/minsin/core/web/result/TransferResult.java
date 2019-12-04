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
public class TransferResult<T> extends BaseResult<TransferResult<T>> {

    @Setter
    @Getter
    @Accessors(chain = true)
    private T value;

    public TransferResult() {
    }

    public TransferResult(ResultOptions options, String msg) {
        super(options, msg);
    }

    public TransferResult(BaseResult baseResult) {
        super(baseResult);
    }

    public TransferResult(ResultOptions resultOptions) {
        super(resultOptions);
    }

    /**
     * 创建Result对象
     *
     * @param type
     * @return
     */
    public Result toResult(String type) {
        boolean success = this.isSuccess();
        return Result.optionalResult(success, type);
    }

    @Override
    public TransferResult<T> setMsg(String message) {
        return super.setMsg(message);
    }

    @Override
    public TransferResult<T> setCode(int code) {
        return super.setCode(code);
    }
}
