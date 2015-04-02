package edu.tcu.mi.springmvc.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.tcu.mi.springmvc.repository.IRepository;

@Service("_GenericServiceImp")
public class GenericServiceImpl<T> implements IService<T> {

	public static Logger logger = Logger.getLogger(GenericServiceImpl.class);

	protected IRepository<T> repository;
	
    @Autowired
    public GenericServiceImpl(IRepository<T> repository) {
		this.repository = repository;
    }

	@Override
	public T save(T entity) {
		repository.save(entity);		
		return entity;
	}
	
	@Override
	public T save(T entity, Class<T> clazz) {
		this.repository.setClazz(clazz);
		this.save(entity);	
		return entity;
	}
	
	@Override
    @Transactional(readOnly = true)
	public synchronized T findById(String _id) {
    	T entity = repository.findById(_id);
		return entity;
	}
	
	@Override
    @Transactional(readOnly = true)
	public synchronized T findById(String _id, Class<T> clazz) {
		repository.setClazz(clazz);
		T entity = this.findById(_id);
		return entity;
	}

	@Override
    @Transactional(readOnly = true)
	public synchronized List<T> findAll() {
		List<T> list = repository.findAll();
		return list;
	}
	
	
	@Override
    @Transactional(readOnly = true)
	public synchronized List<T> findAll(Class<T> clazz) {
		this.repository.setClazz(clazz);
		List<T> list = findAll();
		return list;
	}

	@Override
    @Transactional(readOnly = true)
	public synchronized Page<T> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
    @Transactional(readOnly = true)
	public synchronized Page<T> findAll(Pageable pageable, Class<T> clazz) {
		this.repository.setClazz(clazz);
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    @Transactional(readOnly = true)
	public synchronized Long count() {
		return this.repository.count();
	}
	
	@Override
    @Transactional(readOnly = true)
	public synchronized Long count(Class<T> clazz) {
		this.repository.setClazz(clazz);
		return count();
	}

	@Override
	public boolean delete(String _id) {
		T object = repository.delete(_id);
		return (object != null)? true:false;
	}
	
	@Override
	public boolean delete(String _id, Class<T> clazz) {
		// TODO Auto-generated method stub
		this.repository.setClazz(clazz);
		return delete(_id);
	}

	@Override
    @Transactional(readOnly = true)
	public synchronized boolean exists(String _Id) {
		// TODO Auto-generated method stub
		return false;
	}
	
    @Transactional(readOnly = true)
	public synchronized boolean exists(String _Id, Class<T> clazz) {
		this.repository.setClazz(clazz);
		return exists(_Id);
	}

	@Override
	public List<T> findByEMRId(String _emrId, Class<T> clazz) {
		this.repository.setClazz(clazz);
		List<T>  list = this.repository.findByEMRId(_emrId);
		return list;
		
	}

	@Override
	public T findByUsername(String _username, Class<T> clazz) {
		this.repository.setClazz(clazz);
		T resource = repository.findByUsername(_username);
		return resource;
	}

}
