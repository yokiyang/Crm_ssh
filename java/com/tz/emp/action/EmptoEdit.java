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
import com.tz.entity.Employee;

@SuppressWarnings("serial")
@WebServlet(name = "EmptoEdit", urlPatterns = "/permission/emp/toedit")
public class EmptoEdit extends HttpServlet {
	ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
	EmployeeServiceImpl employeeService=(EmployeeServiceImpl) ac.getBean("employeeServiceImpl");
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Employee emp=employeeService.findById(Long.parseLong(id));
		request.setAttribute("id", id);
		request.setAttribute("emp", emp);
		request.getRequestDispatcher("/permission/editEmp.jsp").forward(request, response);
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
}
