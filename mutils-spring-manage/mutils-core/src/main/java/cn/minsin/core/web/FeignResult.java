package cn.minsin.core.web;

import lombok.Getter;

/**
 * @author: minton.zhang
 * @since: 2019/10/28 10:16
 */
public class FeignResult<T> extends BaseResult {

    @Getter
    private T data;

    public FeignResult() {
        //allow create
    }

    public FeignResult(ResultOptions options, String... msg) {
        super(options, msg);
    }

    public FeignResult(boolean isSuccess, T data) {
        super(isSuccess ? DefaultResultOptions.SUCCESS : DefaultResultOptions.FAIL);
        this.data = data;
    }

    public FeignResult(boolean isSuccess) {
        super(isSuccess ? DefaultResultOptions.SUCCESS : DefaultResultOptions.FAIL);
    }

    public FeignResult data(T value) {
        this.data = value;
        return this;
    }

    public static <T> FeignResult<T> ok(T data) {
        return new FeignResult<>(true, data);
    }


    public static FeignResult<String> fail(ResultOptions options, String message) {
        return new FeignResult<>(options, message);
    }
}
