package com.brd.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.brd.entity.MyFile;

@Repository
public class MyFileDaoImpl implements MyFileDao {
	private final SessionFactory factory;

	public MyFileDaoImpl(SessionFactory factory) {
		super();
		this.factory = factory;
	}

	@Override
	public void addFile(MyFile file) {
		factory.getCurrentSession().save(file);
	}

	@Override
	public void removeFile(MyFile file) {
		factory.getCurrentSession().delete(file);
	}

	@Override
	public MyFile getFile(String fileName) {
		return factory.getCurrentSession().get(MyFile.class,fileName);
	}
	
	
	

	@Override
	public boolean updateReadStatus(String fileName, String status) {
		MyFile myFile = getFile(fileName);
		if(myFile == null)
			return false;
		myFile.setIsFileRead("T");
		factory.getCurrentSession().update(myFile);
		return true;
	}

	@Override
	public List<MyFile> getAllFile() {
		Session session = factory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<MyFile> criteria = builder.createQuery(MyFile.class);
		criteria.from(MyFile.class);
		return session.createQuery(criteria).getResultList();
	}
	
	
	
	

}
