package cn.minsin.core.web;

/**
 * @Author: minton.zhang
 * @Date: 2019/9/22 17:47
 */

@FunctionalInterface
public interface OperationType {

    /**
     * 获取操作类型
     * @return
     */
    String getOperation();
}
