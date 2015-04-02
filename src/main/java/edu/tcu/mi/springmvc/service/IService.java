package edu.tcu.mi.springmvc.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IService<T> {

    T save(T entity); // Saves the given entity.                                                                  

    T findById(String _id);                                                      

    List<T> findAll();                                                                 

    Page<T> findAll(Pageable pageable);                                                  

    Long count();                                                                      

    boolean delete(String _id);        
    
	boolean delete(String _id, Class<T> clazz);                                                  

    boolean exists(String _id);

	Long count(Class<T> clazz);

	Page<T> findAll(Pageable pageable, Class<T> clazz);

	List<T> findAll(Class<T> clazz);

	T findById(String _id, Class<T> clazz);

	T save(T entity, Class<T> clazz);

	List<T> findByEMRId(String _emrId, Class<T> clazz);

	T findByUsername(String _username, Class<T> clazz);

}
