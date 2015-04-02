package edu.tcu.mi.springmvc.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import edu.tcu.mi.springmvc.model.User;
import edu.tcu.mi.springmvc.repository.mongodb.UserDetailsRepositoryImpl;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	public static Logger logger = Logger.getLogger(UserDetailsServiceImpl.class);

	protected UserDetailsRepositoryImpl repository;
	
	@Autowired
    public UserDetailsServiceImpl(UserDetailsRepositoryImpl repository) {
        this.repository = repository;
    }
    
    public UserDetails loadUserByUsername(String username){
    	User user = repository.loadUserByUsername(username);
		return user;
    }
    
}
