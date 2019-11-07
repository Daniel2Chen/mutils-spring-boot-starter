package cn.minsin.core.override;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.lang.reflect.Type;

/**
 * 自动实现深拷贝和浅拷贝接口
 * 基于fastjson {@link JSON#toJSONString()} 实现序列化
 * 基于fastjson {@link JSON#parseObject(String, Class)} ()} 实现反序列化
 *
 * @author: minton.zhang
 * @since: 0.0.8.RELEASE
 */
public interface AutoCloneable<T> extends Serializable {

    /**
     * 用于替换
     * {@link Object#clone()}
     *
     * @return 对象
     */
    default T deepClone() {
        return JSON.parseObject(this.toJsonString(), (Type) this.getClass());
    }


    /**
     * 浅拷贝
     * @return
     */
    @SuppressWarnings("unchecked")
    default T shallowClone() {
        return (T) this;
    }

    /**
     * 将当前对象转换成JSON字符串
     * 使用fastJSON中的{@link JSON#toJSONString(Object)} )}
     *
     * @return
     */
    default String toJsonString() {
        return JSON.toJSONString(this);
    }
}
