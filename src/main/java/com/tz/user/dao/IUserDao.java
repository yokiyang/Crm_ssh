package com.tz.user.dao;


import com.tz.entity.User;

public interface IUserDao {
	
	
	/**
	 * 根据用户名和密码查询指定用户
	 */
	
	User selectUser(String username,String password);
	
}
