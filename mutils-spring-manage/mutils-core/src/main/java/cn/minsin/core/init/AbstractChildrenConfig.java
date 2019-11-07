package cn.minsin.core.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractChildrenConfig {

	protected static Logger slog = LoggerFactory.getLogger(AbstractChildrenConfig.class);
	/**
	 * 	子类配置项检查
	 */
	public abstract void checkConfig();
}
