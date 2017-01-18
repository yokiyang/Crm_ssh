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
import com.tz.util.DateUtil;

@SuppressWarnings("serial")
@WebServlet(name = "EmpAddAction", urlPatterns = "/permission/emp/addOrUpdate")
public class EmpAddAction extends HttpServlet {
	ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
	EmployeeServiceImpl employeeService=(EmployeeServiceImpl) ac.getBean("employeeServiceImpl");
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String base = request.getContextPath();

		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String title = request.getParameter("title");
		String salary = request.getParameter("salary");
		String hiredate = request.getParameter("hiredate");
		Employee employee = new Employee();
		if (id != null) {
			employee.setId(Long.parseLong(id));
		}
		String nextPage = "";
		if((name!=null &&name.length()>0)&&(salary!=null&&salary.length()>0)
				&&(hiredate!=null&&hiredate.length()>0)&&(title!=null&&title.length()>0)){
			employee.setName(name);
			employee.setTitle(title);
			employee.setSalary(Double.parseDouble(salary));
			employee.setHiredate(DateUtil.parseString("yyyy-MM-dd",hiredate));
	
			boolean b = employeeService.registerOrEditEmp(employee);
	
			if (b) {
				nextPage = "/permission/emp/list";
			} else {
				nextPage = "/failure.html";
			}
		}else{
			nextPage = "/failure.html";
		}
		response.sendRedirect(base + nextPage);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
