package com.tz.emp.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tz.emp.service.IEmployeeService;
import com.tz.entity.Employee;

@Controller
public class EmpEditAction{
	@Resource
	private IEmployeeService employeeService;
	
	@RequestMapping("/permission/emp/editSalary/{id}/{salary}")
	public void editSalary(@PathVariable String id,@PathVariable String salary,HttpServletResponse response) throws IOException{
		Employee employee=employeeService.editSalaryById(Long.parseLong(id), Double.parseDouble(salary));
		if(employee!=null){
			response.getWriter().print("0");
		}else{
			response.getWriter().print("1");
		}
	}
}
