package cn.minsin.core.exception;

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

    public MutilsException(java.lang.String msg) {
        super(msg);
    }

    public MutilsException() {
    }

    public MutilsException(Throwable cause, java.lang.String msg) {
        super(msg, cause);
    }

    public MutilsException(Throwable cause) {
        super(cause);
    }

    protected static java.lang.String getMessage(Throwable e, java.lang.String defaultValue) {
        log.error("An error:", e);
        boolean b = e instanceof MutilsException;
        if (b) {
            java.lang.String message = e.getMessage();
            return message == null ? defaultValue : defaultValue.concat(",").concat(message);
        }
        return defaultValue;
    }

    /**
     * condition为true时,抛出异常
     *
     * @param trueCondition
     * @param message
     */
    public static void throwException(boolean trueCondition, java.lang.String message) {
        if (trueCondition) {
            throw new MutilsException(message);
        }
    }

    /**
     * condition为true时,抛出异常
     *
     * @param trueCondition
     */
    public static void throwException(boolean trueCondition) {
        if (trueCondition) {
            throw new MutilsException();
        }
    }


    /**
     * 为空时抛出异常
     *
     * @param object
     * @param message
     */
    public static void notNull(Object object, java.lang.String message) {
        if (object == null) {
            throw new MutilsException(message);
        }
    }
}
