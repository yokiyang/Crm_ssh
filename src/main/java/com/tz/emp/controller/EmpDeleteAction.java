package com.tz.emp.controller;

import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tz.emp.service.IEmployeeService;

@Controller
public class EmpDeleteAction {
	@Resource
	private IEmployeeService employeeService;
	
	@RequestMapping("/permission/emp/{id}/delete")
	public void delete(@PathVariable String id,HttpServletResponse response) throws IOException{
		boolean result = employeeService.removeEmpById(Long.parseLong(id));
		if(result){
			response.getWriter().print("0");
		}else{
			response.getWriter().print("1");
		}
	}
}
