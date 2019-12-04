package cn.minsin.core.web.result;

import lombok.Getter;
import lombok.Setter;

/**
 * @author: minton.zhang
 * @since: 0.0.8.RELEASE
 */
public class FeignResult<T> extends BaseResult<FeignResult<T>> {

    @Getter
    @Setter
    private T value;

    public FeignResult() {
    }

    public FeignResult(ResultOptions options, String msg) {
        super(options, msg);
    }

    public FeignResult(BaseResult baseResult) {
        super(baseResult);
    }

    public FeignResult(ResultOptions resultOptions) {
        super(resultOptions);
    }

    public FeignResult(boolean isSuccess) {
        super(isSuccess);
    }

    @Override
    public FeignResult<T> setMsg(String message) {
        return super.setMsg(message);
    }

    @Override
    public FeignResult<T> setCode(int code) {
        return super.setCode(code);
    }

    public static <T> FeignResult<T> ok(T data) {
        FeignResult<T> tFeignResult = new FeignResult<>(true);
        tFeignResult.setValue(data);
        return tFeignResult;
    }

    public static FeignResult<String> fail(String message) {
        return new FeignResult<String>(DefaultResultOptions.FAIL).setMsg(message);
    }
}
