package edu.tcu.mi.springmvc.repository.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import edu.tcu.mi.springmvc.repository.IRepository;

@Repository
public class JpaGenericRepositoryImpl<T> implements IRepository<T>{
	@PersistenceContext
    private EntityManager em;

	@Override
	public T save(T entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T findById(String _Id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<T> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long count() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T delete(String _id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(String _id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Class<T> getClazz() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setClazz(Class<T> clazz) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<T> findByEMRId(String _documentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T findByUsername(String _username) {
		// TODO Auto-generated method stub
		return null;
	}


	

}
