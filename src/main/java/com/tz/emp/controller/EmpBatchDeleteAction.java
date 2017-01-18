package com.tz.emp.controller;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tz.emp.service.IEmployeeService;
@Controller
public class EmpBatchDeleteAction{
	@Resource
	private IEmployeeService employeeService;
	
	@RequestMapping("/permission/emp/batchdelete")
	public void batchDelete(String ids,HttpServletResponse response) throws IOException{
		String[] idsarr=ids.split(":");
		Long[] eids=new Long[idsarr.length];
		for(int i=0;i<eids.length;i++){
			eids[i]=Long.valueOf(idsarr[i]);
		}
		boolean b=employeeService.removeEmpByIds(eids);
		if(b){
			response.getWriter().print("0");
		}else{
			response.getWriter().print("1");
		}
	}
}
