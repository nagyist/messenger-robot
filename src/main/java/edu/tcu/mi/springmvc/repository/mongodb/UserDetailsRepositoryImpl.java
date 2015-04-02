package edu.tcu.mi.springmvc.repository.mongodb;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import edu.tcu.mi.springmvc.model.User;

@Repository
public class UserDetailsRepositoryImpl {

	public static Logger logger = Logger.getLogger(UserDetailsRepositoryImpl.class);
    
    private MongoTemplate mongoTemplate;

    @Autowired
    public UserDetailsRepositoryImpl(MongoTemplate mongoTemplate){
    	this.mongoTemplate = mongoTemplate;
    	logger.info("mongodb");
    }
    
    public User loadUserByUsername(String username){
    	BasicQuery query = new BasicQuery("{\"username\" : \"" + username + "\"}");
    	User user = mongoTemplate.findOne(query, User.class);
		return user;
    }
}
