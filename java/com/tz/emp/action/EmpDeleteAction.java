package com.tz.emp.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.tz.emp.service.impl.EmployeeServiceImpl;

@SuppressWarnings("serial")
@WebServlet(name = "EmpDeleteAction", urlPatterns = "/permission/emp/delete")
public class EmpDeleteAction extends HttpServlet {

	ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
	EmployeeServiceImpl employeeService=(EmployeeServiceImpl) ac.getBean("employeeServiceImpl");
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		//String base=request.getContextPath();
		//从请求中获取参数
		String id = request.getParameter("id");

		//调用业务层方法执行操作
		boolean result = employeeService.removeEmpById(Long.parseLong(id));
		//String pageSize=request.getParameter("pageSize");
		if (result) {
//			//重新调用查询,展现结果
//			if(pageSize!=null){
//				response.sendRedirect(base+"/permission/emp/list?pageSize="
//			+Integer.parseInt(pageSize));
//			}else{
//				response.sendRedirect(base+"/permission/emp/list");
//
//			}
			out.println("0");
		} else {
			out.println("1");
//			response.sendRedirect(base+"/error.html");
		}
		out.flush();
		out.close();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
