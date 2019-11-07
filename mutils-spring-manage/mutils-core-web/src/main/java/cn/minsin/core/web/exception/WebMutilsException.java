package cn.minsin.core.web.exception;

import cn.minsin.core.constant.MessageConstant;
import cn.minsin.core.exception.MutilsException;
import cn.minsin.core.web.result.DefaultResultOptions;
import cn.minsin.core.web.result.Result;

/**
 * @author: minton.zhang
 * @since: 2019/11/7 19:05
 */
public class WebMutilsException extends MutilsException {

    public static Result getMessageToResult(Throwable e, String operationType) {
        return Result.exception(getMessage(e, MessageConstant.isSuccess(false, operationType)));
    }

    @Override
    public String getMessage() {
        return super.getCause() != null ? DefaultResultOptions.EXCEPTION.getMsg() : super.getMessage();
    }
}
