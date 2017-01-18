package com.tz.emp.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tz.emp.service.impl.EmployeeServiceImpl;
import com.tz.entity.PageBean;

@SuppressWarnings("serial")
@WebServlet(name="EmpListAction",urlPatterns="/permission/emp/list")
public class EmpListAction extends HttpServlet {
	ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
	EmployeeServiceImpl employeeService=(EmployeeServiceImpl) ac.getBean("employeeServiceImpl");
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pageNow=request.getParameter("pageNow");
		String pageSize=request.getParameter("pageSize");
		pageNow=pageNow==null?"1":pageNow;
		pageSize=pageSize==null?"2":pageSize;
		PageBean pageBean =employeeService.findEmpsByPage(Integer.parseInt(pageNow),Integer.parseInt(pageSize));
		//List<Employee> employees=employeeService.findAllEmps();
		//request.getServletContext().setAttribute("employees", employees);
		request.setAttribute("pageBean",pageBean );
		request.getRequestDispatcher("/permission/listEmp.jsp").forward(request, response);
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
