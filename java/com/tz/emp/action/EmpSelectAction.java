package com.tz.emp.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

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
@WebServlet(name = "EmpSelectAction", urlPatterns = "/permission/emp/*")
public class EmpSelectAction extends HttpServlet {
	ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
	EmployeeServiceImpl employeeService=(EmployeeServiceImpl) ac.getBean("employeeServiceImpl");
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
	
		List<Employee> emps=null;
		if ("/selectname".equals(pathInfo)){
			String name=request.getParameter("name");
			emps=employeeService.findByName(name);
		}else if("/selecttitle".equals(pathInfo)){
			String title=request.getParameter("title");
			System.out.println(title);
			emps=employeeService.findByTitle(title);
		}else if("/selectsalary".equals(pathInfo)){
			String minsalary =null;
			minsalary =request.getParameter("minsalary");
			double min=0;
			if(minsalary!=null&&minsalary.length()!=0){
				min=Double.parseDouble(minsalary);
			}else{
				min=0;
			}
			String maxsalary =null;
			maxsalary =request.getParameter("maxsalary");
			double max=0;
			if(maxsalary!=null&&maxsalary.length()!=0){
				max=Double.parseDouble(maxsalary);
			}else{
				//max=Double.MAX_VALUE;
				max=1000000;
			}
			emps=employeeService.findBySalary(min, max);
		}else if("/selecthiredate".equals(pathInfo)){
			String begindate=null;
			begindate=request.getParameter("begin");
			Date begin=null;;
			if(begindate!=null){
				begin=DateUtil.parseString("yyyy-MM-dd", begindate);
			}else{
				begin=DateUtil.parseString("yyyy-MM-dd", "1970-01-01");
			}
			String enddate=null;
			enddate=request.getParameter("end");
			Date end=null;
			if(enddate!=null){
				end=DateUtil.parseString("yyyy-MM-dd", enddate);
			}else{
				end=new Date();
			}
			emps=employeeService.findByHireDate(begin, end);
		}
		request.setAttribute("emps", emps);
		request.getRequestDispatcher("/permission/selectEmp.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
}
