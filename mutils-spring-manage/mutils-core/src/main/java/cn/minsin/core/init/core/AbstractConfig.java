package cn.minsin.core.init.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 初始化基类
 *
 * @author mintonzhang
 * @date 2019年1月1日
 * @since 0.1.0
 */
public abstract class AbstractConfig {

    @NestedConfigurationProperty
    protected final static Logger slog = LoggerFactory.getLogger(AbstractConfig.class);

    @NestedConfigurationProperty
    private final static Map<Type, AbstractConfig> localProperties = new ConcurrentHashMap<>();


    public static void init(Class<?> configClazz, AbstractConfig config) {
        config.checkConfig();
        localProperties.put(configClazz, config);
    }

    /**
     * 子类配置项检查
     */
    protected abstract void checkConfig();


    @SuppressWarnings("unchecked")
    public static <T extends AbstractConfig> T loadConfig(Class<T> configClazz) {
        if (configClazz == null || !localProperties.containsKey(configClazz)) {
            return null;
        }
        return (T) localProperties.get(configClazz);
    }
}
