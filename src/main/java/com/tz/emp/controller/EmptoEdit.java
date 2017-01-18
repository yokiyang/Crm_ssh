package com.tz.emp.controller;


import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tz.emp.service.IEmployeeService;
import com.tz.entity.Employee;

@Controller
public class EmptoEdit{
	@Resource
	private IEmployeeService employeeService;
	
	@RequestMapping("/permission/emp/{id}/toedit")
	private String toEdit(@PathVariable String id,Model model){
		Employee employee=employeeService.findById(Long.parseLong(id));
		model.addAttribute("id", id);
		model.addAttribute("emp", employee);
		return "/permission/editEmp.jsp";
	}
}
