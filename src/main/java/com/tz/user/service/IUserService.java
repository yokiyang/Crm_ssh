package com.tz.user.service;


import com.tz.entity.User;

public interface IUserService {

	/**
	 * 登陆
	 */
	User login(String username, String password);
}
