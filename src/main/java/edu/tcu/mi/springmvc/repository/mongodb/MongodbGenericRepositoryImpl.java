package edu.tcu.mi.springmvc.repository.mongodb;

import java.util.List;

import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import edu.tcu.mi.springmvc.repository.IRepository;

@Repository
public class MongodbGenericRepositoryImpl<T> implements IRepository<T>{

	public static Logger logger = Logger.getLogger(MongodbGenericRepositoryImpl.class);
    
    private MongoTemplate mongoTemplate;
    
    private Class<T> clazz = null;
    
    @Autowired
    public MongodbGenericRepositoryImpl(MongoTemplate mongoTemplate){
    	this.mongoTemplate = mongoTemplate;
    	logger.info("mongodb");
    }

	@Override
	public T findById(String _id) {
		if(clazz != null){
			T resource = mongoTemplate.findOne(new BasicQuery("{ id : \"" + _id + "\"}"), clazz);
			if(resource == null){
//				resource = mongoTemplate.findById(new ObjectId(_id), clazz);
			}
			clazz = null;
			return resource;
		} else {
			return null;
		}
	}

	@Override
	public List<T> findByEMRId(String emrId) {
		if(clazz != null){
			// http://examples.lishman.com/spring/data-mongodb/mongotemplate-queries.html
			List<T> resource = mongoTemplate.find(new BasicQuery("{ \"emrId\" : \"" + emrId + "\"}"), clazz);
			clazz = null;
			return resource;
		} else {
			return null;
		}
	}

	@Override
	public List<T> findAll() {
		logger.info(clazz);
		if(clazz != null){
			logger.info(clazz.getName());
			List<T> list = mongoTemplate.findAll(clazz);
			clazz = null;
			return list;
		} else {
			logger.info("You miss the class!");
			return null;
		}
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		clazz = null;
		return null;
	}

	@Override
	public Long count() {
		if(clazz != null){
			Long count = mongoTemplate.count(new Query(), clazz);
			clazz = null;
			return count;
		} else {
			return null;
		}
	}
	
	@Override
	public T save(T entity) {
		if(!mongoTemplate.collectionExists(entity.getClass())){
			logger.info("mongodb \t" + entity.getClass());
			mongoTemplate.createCollection(entity.getClass());
			mongoTemplate.insert(entity);
		} else {
			logger.info("mongodb \t" + entity.getClass().getSimpleName());
			mongoTemplate.save(entity);
		}
		clazz = null;
		return entity;
	}
	

	@Override
	public T delete(String _id) {
		T resource = mongoTemplate.findAndRemove(new BasicQuery("{id : \"" + _id + "\"}"), clazz);
		clazz = null;
		return resource;
	}

	@Override
	public boolean exists(String _id) {
		T resource = mongoTemplate.findById(new ObjectId(_id), clazz);
		clazz = null;
		if(resource == null) return false;
		return true;
	}

	@Override
	public Class<T> getClazz() {
		return this.clazz;
	}

	@Override
	public void setClazz(Class<T> clazz) {
		 this.clazz = clazz;
	}

	@Override
	public T findByUsername(String _username) {
		T resource = mongoTemplate.findOne(new BasicQuery("{\"username\" : \"" + _username + "\"}"), clazz);
		clazz = null;
		return resource;
	}
}
