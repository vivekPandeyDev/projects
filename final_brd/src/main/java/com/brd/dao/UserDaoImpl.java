package com.brd.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.brd.entity.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	private final SessionFactory factory;

	public UserDaoImpl(SessionFactory factory) {
		super();
		this.factory = factory;
	}

	@Override
	public User getUserByName(String name) {
		return factory.getCurrentSession().get(User.class, name);
	}

	@Override
	public boolean addUser(User user) {
		Session session = factory.getCurrentSession();
		if (session.get(User.class, user.getUsername()) != null) {
			return false;
		}
		session.save(user);
		return true;
	}

	@Override
	public void removeUser(User user) {
		Session session = factory.getCurrentSession();
		session.delete(user);
	}

	@Override
	public void updateUser(User user) {
		Session session = factory.getCurrentSession();
		session.update(user);
	}
	
	

	@Override
	public void updateUserRole(String username, List<String> roles) {
		Session session = factory.getCurrentSession();
		User user =  session.get(User.class, username);
		user.setAuthorities(roles);
		session.update(user);
	}

	@Override
	public List<User> getAllUsers() {
		Session session = factory.getCurrentSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<User> criteria = builder.createQuery(User.class);
		criteria.from(User.class);
		return session.createQuery(criteria).getResultList();
	}
	

}
