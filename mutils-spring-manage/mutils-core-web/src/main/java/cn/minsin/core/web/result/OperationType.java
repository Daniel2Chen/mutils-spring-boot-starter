package cn.minsin.core.web.result;

import cn.minsin.core.tools.StringUtil;

/**
 * @Author: minton.zhang
 * @Date: 2019/9/22 17:47
 * @since: 0.0.8.RELEASE
 */
public interface OperationType {

    String DELETE = "删除";
    String COMMIT = "提交";
    String INSERT = "添加";
    String UPDATE = "修改";
    String SELECT = "查询";
    String CANCEL = "取消";
    String GENERATE = "生成";


    String SIGN_IN = "登录";
    String SIGN_OUT = "注销";
    String SIGN_UP = "注册";

    String DO = "操作";


    String CHECK = "校验";
    String REVIEW = "审核";
    String CONFIRM = "确认";
    String RECALL = "撤回";


    String SEND = "发送";
    String PUSH = "推送";
    String NOTIFY = "通知";

    String EXPORT = "导出";
    String IMPORT = "导入";

    String URGE = "催促";

    String MATCH = "匹配";
    String BIND = "绑定";
    String UNBIND = "解绑";

    String AUTHORIZATION = "授权";

    String PAY = "支付";
    String TRANSFER = "转账";
    String WITHDRAW = "提现";
    String REFUND = "退款";

    String DISABLE = "禁用";
    String ENABLE = "启用";



    /**
     * 自动选择类型 只适用于 添加和修改
     *
     * @param id 需要判断的值
     * @return
     */
    static String AUTO_CHOOSE(CharSequence id) {
        return StringUtil.isNotBlank(id) ? OperationType.UPDATE : OperationType.INSERT;
    }

    /**
     * 自动选择类型 只适用于 添加和修改
     *
     * @param condition 需要判断的值
     * @return
     */
    static String AUTO_CHOOSE(boolean condition) {

        return condition ? OperationType.UPDATE : OperationType.INSERT;
    }

    /**
     * 手动输入
     *
     * @param typeName
     * @return
     */
    static String INPUT(String typeName) {
        return typeName;
    }

}
