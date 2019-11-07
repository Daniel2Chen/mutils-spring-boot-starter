/**
 *
 */
package cn.minsin.core.web.result;

import cn.minsin.core.constant.MessageConstant;
import cn.minsin.core.web.exception.WebMutilsException;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * 	构建者模式的Result 
 * Eg：Result.builderMissParamter().data('name',"张三")
 * @author mintonzhang
 * @since 0.1.0
 */
@Getter
public class Result extends BaseResult<Result> {

    private static final long serialVersionUID = 1L;


    public Result(ResultOptions options, java.lang.String... msg) {
        super(options, msg);
    }

    public Result() {
        //allow create
    }

    private HashMap<String, Object> multidata;

    public Result data(java.lang.String key, Object value) {
        if (multidata == null) {
            multidata = new HashMap<>(3);
        }
        multidata.put(key, value);
        return this;
    }

    /**
     *批量新增数据
     * @param data
     * @return
     */
    public Result data(Map<java.lang.String, Object> data) {
        this.multidata.putAll(data);
        return this;
    }


    /**
     * 构造result
     *
     * @param option  需要实现接口ResultOptions 的枚举 默认枚举是 DefaultResultOptions
     * @param message 提示给前端的消息
     * @return
     */
    public static Result builder(ResultOptions option, java.lang.String... message) {
        return new Result(option, message);
    }

    /**
     * code 来自Result中的 SUCCESS 或 EXCEPTION
     *
     * @param msg this default is '操作成功'
     */
    public static Result ok(java.lang.String... msg) {
        return builder(DefaultResultOptions.SUCCESS, msg);
    }

    /**
     * code 来自Result中的 SUCCESS 或 EXCEPTION
     *
     * @param msg this default is '服务器异常'
     */
    public static Result exception(java.lang.String... msg) {
        return builder(DefaultResultOptions.EXCEPTION, msg);
    }

    /**
     * 构建缺少参数的Result
     *
     * @param msg this default is '缺少必要参数'
     */
    public static Result missParam(java.lang.String... msg) {
        return builder(DefaultResultOptions.MISS_PARAM, msg);
    }

    /**
     * 构建失败消息
     *
     * @param msg this default is '操作失败'
     */
    public static Result fail(java.lang.String... msg) {
        return builder(DefaultResultOptions.FAIL, msg);
    }

    /**
     * 构建用户过期
     *
     * @param msg this default is '用户已失效'
     */
    public static Result timeout(java.lang.String... msg) {
        return builder(DefaultResultOptions.OUT_TIME, msg);
    }

    /**
     * 构建错误
     *
     * @param msg this default is '服务器跑路了，请稍后重试'
     */
    public static Result error(java.lang.String... msg) {
        return builder(DefaultResultOptions.ERROR, msg);
    }

    /**
     * 快速判断 新增和修改
     * 推荐 在新增和修改时使用
     *
     * @param id        用于判断是新增或修改的数据
     * @param isSuccess 操作是否成功
     * @return
     */
    public static Result builderOptionalResult(boolean isSuccess, CharSequence id) {
        return builder(isSuccess ? DefaultResultOptions.SUCCESS : DefaultResultOptions.FAIL,
                MessageConstant.isSuccess(isSuccess, OperationType.AUTO_CHOOSE(id)));
    }

    /**
     * 获取公共提示消息并封装成Result
     * 推荐 非新增和修改时使用
     *
     * @param type      用于判断操作是否成功
     * @param isSuccess 操作是否成功
     * @return
     */
    public static Result builderOptionalResult(boolean isSuccess, String type) {
        return builder(isSuccess ? DefaultResultOptions.SUCCESS : DefaultResultOptions.FAIL,
                MessageConstant.isSuccess(isSuccess, type));
    }

    /**
     * 解析Exception中的异常
     *
     * @param e
     * @param type
     * @return
     */
    public static Result builderOptionalResult(Throwable e, String type) {
        return WebMutilsException.getMessageToResult(e, type);
    }

}