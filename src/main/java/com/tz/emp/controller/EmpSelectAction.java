package com.tz.emp.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tz.emp.service.IEmployeeService;
import com.tz.entity.Employee;
import com.tz.util.DateUtil;

@Controller
public class EmpSelectAction{
	@Resource
	private IEmployeeService employeeService;
	@RequestMapping("/permission/emp/selectname")
	public String selectname(String name,Model model){
		List<Employee> emps=employeeService.findByName(name);
		model.addAttribute("emps", emps);
		return "/WEB-INF/page/selectEmp.jsp";
	}
	
	@RequestMapping("/permission/emp/selecttitle")
	public String selecttitle(String title,Model model){
		List<Employee> emps=employeeService.findByTitle(title);
		model.addAttribute("emps", emps);
		return "/WEB-INF/page/selectEmp.jsp";
	}
	@RequestMapping("/permission/emp/selectsalary")
	public String selectsalary(String minsalary,String maxsalary,Model model){
		double min=0;
		if(minsalary!=null&&minsalary.length()!=0){
			min=Double.parseDouble(minsalary);
		}else{
			min=0;
		}
		double max=0;
		if(maxsalary!=null&&maxsalary.length()!=0){
			max=Double.parseDouble(maxsalary);
		}else{
			//max=Double.MAX_VALUE;
			max=1000000;
		}
		
		List<Employee> emps=employeeService.findBySalary(min, max);
		model.addAttribute("emps", emps);
		return "/WEB-INF/page/selectEmp.jsp";
	}
	
	@RequestMapping("/permission/emp/selecthiredate")
	public String selecthiredate(String begin,String end,Model model){
		Date beg=null;;
		if(begin!=null){
			beg=DateUtil.parseString("yyyy-MM-dd", begin);
		}else{
			beg=DateUtil.parseString("yyyy-MM-dd", "1970-01-01");
		}
		Date e=null;
		if(end!=null){
			e=DateUtil.parseString("yyyy-MM-dd", end);
		}else{
			e=new Date();
		}
		List<Employee> emps=employeeService.findByHireDate(beg, e);
		model.addAttribute("emps", emps);
		return "/WEB-INF/page/selectEmp.jsp";
	}
}
