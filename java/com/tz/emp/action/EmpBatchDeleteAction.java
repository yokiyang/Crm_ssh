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
@WebServlet(name="EmpBatchDeleteAction",urlPatterns="/permission/emp/batchdelete")
public class EmpBatchDeleteAction extends HttpServlet {

	ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
	EmployeeServiceImpl employeeService=(EmployeeServiceImpl) ac.getBean("employeeServiceImpl");
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		//String base=request.getContextPath();
		//String nextpage="";
		//String pageSize=request.getParameter("pageSize");
		//String[] checkArr=request.getParameterValues("check");
		String ids=request.getParameter("ids");
		String[] idsarr=ids.split(":");
		Long[] eids=new Long[idsarr.length];
		for(int i=0;i<eids.length;i++){
			eids[i]=Long.valueOf(idsarr[i]);
		}
		boolean b=employeeService.removeEmpByIds(eids);
		if(b){
			out.println("0");
		}else{
			out.println("1");
		}
//		if(checkArr!=null){
//			Long[] ids=new Long[checkArr.length];
//			for(int i=0;i<ids.length;i++){
//				ids[i]=Long.valueOf(checkArr[i]);
//			}
//			boolean b=employeeService.removeEmpByIds(ids);
//			if (b) {
//				out.println("0");
//				//重新调用查询,展现结果
////				nextpage="/permission/emp/list?pageSize="
////			+Integer.parseInt(pageSize);
//			} else {
//				out.println("1");
//				//nextpage="/error.html";
//				
//			}
//		}else{
//			nextpage="/error.html";
//		}
//		response.sendRedirect(base+nextpage);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
