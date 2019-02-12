package cn.minsin.core.init.core;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.minsin.core.exception.MutilsException;

public abstract class InitConfig {

	protected static Logger slog = LoggerFactory.getLogger(InitConfig.class);

	private final static Set<InitConfig> starters = new HashSet<>();
	
	private final static Map<Type,InitConfig> loadedConfig = new HashMap<>();

	private static boolean isInit = false;

	protected InitConfig() {
		starters.add(this);
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
			for (InitConfig config : starters) {
				Type class1 = config.getClass();
				if (clazz.equals(class1)) {
					String artifactId = mutilsFunctions.getArtifactId();
					config.checkConfig();
					loadedConfig.put(class1, config);
					slog.info("{} initialized successfully.", artifactId);
				}
			}
		}
	}

	protected abstract void checkConfig();
	
	
	@SuppressWarnings("unchecked")
	public static <T extends InitConfig> T loadConfig(Class<T> configClazz){
		if(configClazz==null||!loadedConfig.containsKey(configClazz)) {
			throw new MutilsException("Cant't find Configuation of '"+configClazz.getName()+"' ,Maybe The configuration file is not initialized or empty, Please check the configuration file or select functions.");
		}
		return (T) loadedConfig.get(configClazz);
	}
}
