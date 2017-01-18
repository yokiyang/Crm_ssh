package com.tz.emp.dao;

import java.util.Date;
import java.util.List;

import com.tz.entity.Employee;
import com.tz.entity.PageBean;

public interface IEmployeeDao {
	/**
	 * 查询所有员工信息
	 */
	List<Employee> selectAllEmps();
	
	boolean deleteEmpById(Long id);
	
	boolean saveOrUpdateEmp(Employee emp);
	
	Employee selectById(Long id);
	
	/**
	 * 根据姓名模糊查询
	 */
	List<Employee> selectByName(String name);
	
	/**
	 * 根据职称查询
	 */
	List<Employee> selectByTitle(String title);
	
	/**
	 * 根据薪资范围查询
	 */
	
	List<Employee> selectBySalary(double min, double max);
	
	/**
	 * 根据雇佣日期查询
	 */
	List<Employee> selectByHireDate(Date begin, Date end);
	
	/**
	 * 
	 */
	boolean deleteEmpByIds(Long[] ids);
	
	List<Employee> selectByPage(int page,int pageSize);
	
	PageBean selectEmpsByPage(int pageNow,int pageSize);
	
	Employee updateSalaryById(Long id,double salary);
}
