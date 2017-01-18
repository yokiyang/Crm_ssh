package com.tz.emp.service;

import java.util.Date;
import java.util.List;

import com.tz.entity.Employee;
import com.tz.entity.PageBean;

public interface IEmployeeService {
	/**
	 * 查找所有员工信息
	 */
	List<Employee> findAllEmps();
	
	boolean removeEmpById(Long id);
	
	boolean registerOrEditEmp(Employee emp);
	
	Employee findById(Long id);
	
	/**
	 * 根据姓名模糊查询
	 */
	List<Employee> findByName(String name);
	
	/**
	 * 根据职称查询
	 */
	List<Employee> findByTitle(String title);
	
	/**
	 * 根据薪资范围查询
	 */
	
	List<Employee> findBySalary(double min, double max);
	
	/**
	 * 根据雇佣日期查询
	 */
	List<Employee> findByHireDate(Date begin, Date end);
	
	boolean removeEmpByIds(Long[] ids);


	/**
	 * 查询总记录数
	 */
	long getRowCount();
	
	List<Employee> findByPage(int page,int pageSize);
	
	PageBean findEmpsByPage(int pageNow,int pageSize);
	
	Employee editSalaryById(Long id,double salary);

}
