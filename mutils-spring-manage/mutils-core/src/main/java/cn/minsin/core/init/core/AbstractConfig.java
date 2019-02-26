package cn.minsin.core.init.core;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.minsin.core.exception.MutilsException;

/**
 * 	初始化基类
 * @author mintonzhang
 * @date 2019年1月1日
 * @since 0.1.0
 */
 public abstract class AbstractConfig {

	protected static Logger slog = LoggerFactory.getLogger(AbstractConfig.class);

	private final static List<AbstractConfig> MODULES = new ArrayList<>();
	
	private final static Map<Type,AbstractConfig> LOADED_CONFIG = new HashMap<>();

	private static boolean isInit = false;

	protected AbstractConfig() {
		MODULES.add(this);
	}

	public static void init(MutilsFunctions[] functions) {
		if (!isInit) {
			isInit = true;
		} else {
			slog.error("The function has been initialized and the initialization failed.");
			return;
		}
		if (functions == null) {
			slog.info("Function initialized failed, Please check config.");
			return;
		}
		slog.info("The selected function is about to be initialized.");

		for (MutilsFunctions mutilsFunctions : functions) {
			Type clazz = mutilsFunctions.getClazz();
			for (AbstractConfig config : MODULES) {
				Type class1 = config.getClass();
				if (clazz.equals(class1)) {
					String artifactId = mutilsFunctions.getArtifactId();
					config.checkConfig();
					LOADED_CONFIG.put(class1, config);
					slog.info("'{}' initialized successfully.", artifactId);
				}
			}
		}
		MODULES.clear();
	}

	/**
	 * 	子类配置项检查
	 */
	protected abstract void checkConfig();
	
	
	@SuppressWarnings("unchecked")
	public static <T extends AbstractConfig> T loadConfig(Class<T> configClazz){
		if(configClazz==null||!LOADED_CONFIG.containsKey(configClazz)) {
			throw new MutilsException("Cant't find Configuation of '"+configClazz.getName()+"' ,Maybe The configuration file is not initialized or empty, Please check the configuration file or select functions.");
		}
		return (T) LOADED_CONFIG.get(configClazz);
	}
}
