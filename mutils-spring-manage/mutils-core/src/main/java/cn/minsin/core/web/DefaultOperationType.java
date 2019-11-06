package cn.minsin.core.web;

import cn.minsin.core.tools.StringUtil;

/**
 * 默认操作类型
 * @Author: minton.zhang
 * @Date: 2019/9/22 17:23
 */
public enum DefaultOperationType implements OperationType {

    DELETE("删除"),
    INSERT("添加"),
    COMMIT("提交"),
    UPDATE("修改"),
    SELECT("查询"),
    CHECK("校验"),
    REVIEW("审核"),
    CONFIRM("确认"),
    SEND("发送"),
    EXPORT("导出"),
    CANCEL("取消"),
    MATCH("匹配"),
    BIND("绑定"),
    DO("操作"),
    AUTHORIZATION("授权"),
    GENERATE("生成"),
    REFUND("退款"),
    RECALL("撤回"),
    STATUS_CHANGE("状态变更");

    private String operation;

    DefaultOperationType(String operation) {
        this.operation = operation;
    }

    /**
     * 自动选择类型 只适用于 添加和修改
     *
     * @param id 需要判断的值
     * @return
     */
    public static DefaultOperationType AUTO_CHOOSE(CharSequence id) {
        return StringUtil.isNotBlank(id) ? DefaultOperationType.UPDATE : DefaultOperationType.INSERT;
    }

    /**
     * 自动选择类型 只适用于 添加和修改
     *
     * @param condition 需要判断的值
     * @return
     */
    public static DefaultOperationType AUTO_CHOOSE(boolean condition) {
        return condition ? DefaultOperationType.UPDATE : DefaultOperationType.INSERT;
    }

    @Override
    public String getOperation() {
        return operation;
    }
}
