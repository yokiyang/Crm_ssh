package com.tz.user.dao.impl;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tz.entity.User;
import com.tz.user.dao.IUserDao;

@Repository
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class UserDaoImpl implements IUserDao {
	@Resource
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public User selectUser(final String username, final String password) {
		return (User) getSession()
				.createQuery(
						"from User u where u.username=:username and u.password=:password")
				.setString("username", username)
				.setString("password", password).uniqueResult();
	}
}
