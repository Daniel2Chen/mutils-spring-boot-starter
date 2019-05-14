/**
 * 
 */
package cn.minsin.config;

import java.io.Serializable;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import org.springframework.data.mongodb.repository.support.QuerydslMongoPredicateExecutor;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;

import cn.minsin.base.BaseRepositoryImpl;

/**
 * @author mintonzhang
 * 2018年10月30日
 */
public class MutilsRepositoryFactory<S, ID extends Serializable> extends MongoRepositoryFactory {
	
	private final MongoOperations mongoOperations;

	public MutilsRepositoryFactory(MongoOperations mongoOperations) {
		super(mongoOperations);
		this.mongoOperations = mongoOperations;
	}

	@Override
	protected Object getTargetRepository(RepositoryInformation information) {
		@SuppressWarnings("unchecked")
		Class<S> repositoryInterface = (Class<S>) information.getRepositoryInterface();
		if (isQueryDslRepository(repositoryInterface)) {
			MongoEntityInformation<S, ID> entityInformation = super.getEntityInformation(repositoryInterface);
			return new SimpleMongoRepository<S, ID>(entityInformation, mongoOperations);
		} else {
			return super.getTargetRepository(information);
		}
		
	}
	
	private boolean isQueryDslRepository(Class<?> repositoryInterface) {
		return QuerydslMongoPredicateExecutor.class.isAssignableFrom(repositoryInterface);
	}

	@Override
	protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
		return isQueryDslRepository(metadata.getRepositoryInterface()) ? super.getRepositoryBaseClass(metadata)
				: BaseRepositoryImpl.class;
	}
	
	
	

}
