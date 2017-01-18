package com.tz.user.service.impl;


import com.tz.entity.User;
import com.tz.user.dao.IUserDao;
import com.tz.user.service.IUserService;

public class UserServiceImpl implements IUserService {

	private IUserDao userDao;

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User login(String username, String password) {
		User user=userDao.selectUser(username, password);
		if(user!=null){
			return userDao.selectUser(username, password);
		}else{
			return null;
		}
	}
}
