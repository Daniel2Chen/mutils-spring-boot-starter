package cn.minsin.config;

import java.io.Serializable;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactoryBean;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;



public class MutilsCustomMongoRepositoryFactoryBean<T extends MongoRepository<S, ID>, S, ID extends Serializable>
		extends MongoRepositoryFactoryBean<T, S, ID> {

	/**
	 * @param repositoryInterface
	 */
	public MutilsCustomMongoRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
		super(repositoryInterface);
	}

	@Override
	protected RepositoryFactorySupport getFactoryInstance(MongoOperations operations) {
		return new MutilsRepositoryFactory<>(operations);
	}
}