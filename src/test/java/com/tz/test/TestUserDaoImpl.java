package com.tz.test;


import javax.annotation.Resource;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.tz.entity.User;
import com.tz.user.service.IUserService;

@ContextConfiguration(value = "classpath:applicationContext.xml")
public class TestUserDaoImpl extends AbstractTestNGSpringContextTests{
	
	@Resource
	private IUserService userService;
	
	@Resource
	private LocalSessionFactoryBean lsfb;
	
	@Test(priority=0)
	public void testDDL(){
		Configuration cfg = lsfb.getConfiguration();
		SchemaExport export = new SchemaExport(cfg);
		export.create(true, true);
	}
	
	@Test(priority=1)
	public void testSelectUser(){
		User user=userService.login("jack", "123");
		System.out.println(user);
	}
}
