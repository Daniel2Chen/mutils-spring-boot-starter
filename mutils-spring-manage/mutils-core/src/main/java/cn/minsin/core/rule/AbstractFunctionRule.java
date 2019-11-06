package cn.minsin.core.rule;

import cn.minsin.core.init.core.AbstractConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Functions 所需要继承的基类
 * 
 * @author minsin
 *
 */
public abstract class AbstractFunctionRule {

	protected static final Logger LOGGER = LoggerFactory.getLogger(AbstractFunctionRule.class);

	protected AbstractFunctionRule(){

    }

    protected static void checkProperties(AbstractConfig config, Class<?> clazz) {
        if (config == null) {
            LOGGER.error("The configuration file named '{}' was not found, you can't use its function, otherwise NullPointException", clazz);
        }
    }
}
