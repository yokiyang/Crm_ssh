package com.tz.emp.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tz.emp.service.IEmployeeService;
import com.tz.entity.Employee;
import com.tz.util.DateUtil;

@Controller
public class EmpAddAction {
	@Resource
	private IEmployeeService employeeService;

	@RequestMapping("/permission/emp/addOrUpdate")
	public String addOrUpdate(String name, String salary, String hiredate,
			String title, String id, HttpServletRequest req) {
		Employee employee = new Employee(name, title,
				Double.parseDouble(salary), DateUtil.parseString("yyyy-MM-dd",
						hiredate));
		if (id != null) {
			employee.setId(Long.parseLong(id));
		}
		boolean b = employeeService.registerOrEditEmp(employee);
		if (b) {
			return "redirect:/permission/emp/1/2/list.do";
		} else {
			return "redirect:failure.html";
		}
	}

}
