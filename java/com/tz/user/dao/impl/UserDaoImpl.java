package com.tz.user.dao.impl;


import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.tz.entity.User;
import com.tz.user.dao.IUserDao;
import com.tz.util.HibernateTemplates;
import com.tz.util.IHibernateCallBack;

public class UserDaoImpl implements IUserDao{

	@Override
	public User selectUser(final String username, final String password) {
		return (User) HibernateTemplates.execute(new IHibernateCallBack() {
			@Override
			public Object execute(Session ses) throws HibernateException {
				return ses.createQuery("from User u where u.username=:username and u.password=:password").setString("username", username).setString("password", password).uniqueResult();
			}
		});
	}	
}
