package edu.tcu.mi.springmvc.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;

public interface IRepository<T> {

    T save(T entity); // Saves the given entity.                                                                  

    T findById(String _Id);                                                      

    List<T> findAll();                                                                 

    Page<T> findAll(Pageable pageable);                                                  

	Long count();                                                         

	T delete(String _id);                                             

	boolean exists(String _id);
    


	public Class<T> getClazz() ;

	public void setClazz(Class<T> clazz);

	List<T> findByEMRId(String _documentId);
	
	T findByUsername(String _username);

}