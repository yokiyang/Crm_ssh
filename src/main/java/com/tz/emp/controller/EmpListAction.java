package com.tz.emp.controller;


import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tz.emp.service.IEmployeeService;
import com.tz.entity.PageBean;

@Controller
public class EmpListAction{
	@Resource
	private IEmployeeService employeeService;
	
	@RequestMapping(value="/permission/emp/{pageNow}/{pageSize}/list",method=RequestMethod.GET)
	public String list(@PathVariable String pageNow,@PathVariable String  pageSize,Model model){
		pageNow=pageNow==null?"1":pageNow;
		pageSize=pageSize==null?"2":pageSize;
		PageBean pageBean =employeeService.findEmpsByPage(Integer.parseInt(pageNow),Integer.parseInt(pageSize));
		model.addAttribute("pageBean",pageBean);
		return "/WEB-INF/page/listEmp.jsp";
	}
}
