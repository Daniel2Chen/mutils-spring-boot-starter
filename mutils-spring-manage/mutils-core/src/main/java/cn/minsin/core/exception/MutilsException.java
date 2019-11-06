package cn.minsin.core.exception;

import cn.minsin.core.constant.MessageConstant;
import cn.minsin.core.web.DefaultResultOptions;
import cn.minsin.core.web.OperationType;
import cn.minsin.core.web.Result;
import lombok.extern.slf4j.Slf4j;

/**
 * 框架内发生一般异常时抛出
 *
 * @author minsin
 */
@Slf4j
public class MutilsException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = -1254579703396031354L;

    public MutilsException(String msg) {
        super(msg);
    }

    public MutilsException() {
    }

    public MutilsException(Throwable cause, String msg) {
        super(msg, cause);
    }

    public MutilsException(Throwable cause) {
        super(cause);
    }

    private static String getMessage(Exception e, String defaultValue) {
        log.error("An error:", e);
        boolean b = e instanceof MutilsException;
        if (b) {
            String message = e.getMessage();
            return message == null ? defaultValue : defaultValue.concat(",").concat(message);
        }
        return defaultValue;
    }

    public static Result getMessageToResult(Exception e, OperationType operationType) {
        return Result.exception(getMessage(e, MessageConstant.isSuccess(false, operationType)));
    }

    @Override
    public String getMessage() {
        return super.getCause() != null ? DefaultResultOptions.EXCEPTION.getMsg() : super.getMessage();
    }

    public static void throwException(boolean trueCondition, String message) {
        if (trueCondition) {
            throw new MutilsException(message);
        }
    }

    public static void throwException(boolean trueCondition) {
        if (trueCondition) {
            throw new MutilsException();
        }
    }

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new MutilsException(message);
        }
    }
}
