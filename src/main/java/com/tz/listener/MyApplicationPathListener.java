package com.tz.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**监听器*/
@WebListener
public class MyApplicationPathListener implements ServletContextListener {

	//监听应用的初始化
	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		String path = context.getContextPath();
		context.setAttribute("base", path);
	}

	//监听应用的销毁
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		context.removeAttribute("base");
	}
}
