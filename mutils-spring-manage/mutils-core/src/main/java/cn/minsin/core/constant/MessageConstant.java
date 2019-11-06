package cn.minsin.core.constant;

import cn.minsin.core.web.OperationType;

/**
 * @author: minton.zhang
 * @since: 2019/11/5 10:33
 */
public interface MessageConstant {

    String NOT_NULL_MESSAGE = "数据验证失败,%s不允许为空";

    String VERIFICATION_NOT_ALL_PASS = "数据校验未完全通过";

    String UNKNOWN_EXCEPTION_MESSAGE = "数据验证失败,%s校验失败";

    String UNFILLED_ITEM="必填项不能为空,请检查.";

    String NULL = "无";

    String SUCCESS = "成功";

    String FAIL= "失败";


    /**
     * 判断是否成功
     *
     * @param isSuccess
     * @return
     */
    static String isSuccess(boolean isSuccess) {
        return isSuccess ? SUCCESS : FAIL;
    }


    /**
     * 判断是否成功并且添加
     * @param isSuccess
     * @param operationType
     * @return
     */
    static String isSuccess(boolean isSuccess, OperationType operationType) {
        return operationType.getOperation().concat(isSuccess(isSuccess));
    }
}
