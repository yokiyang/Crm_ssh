package com.tz.test;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.tz.emp.dao.IEmployeeDao;
import com.tz.emp.service.IEmployeeService;
import com.tz.entity.Employee;
import com.tz.util.DateUtil;

@ContextConfiguration(value = "classpath:applicationContext.xml")
public class TestEmployeeDaoImpl extends AbstractTestNGSpringContextTests{
	
	@Resource
	private IEmployeeService employeeService;
	
	@Resource
	private IEmployeeDao employeeDao;
	
	@Resource
	private LocalSessionFactoryBean lsfb;	
	
	@Test
	public void testSelectAll(){
		List<Employee> employees =employeeDao.selectAllEmps();
		System.out.println(employees);
	}
	@Test(priority = 0)
	public void testDDL(){
		Configuration cfg = lsfb.getConfiguration();
		SchemaExport export = new SchemaExport(cfg);
		export.create(true, true);
	}
	
	@Test
	public void testSelectAllEmps(){
		List<Employee> employees=employeeService.findAllEmps();
		if(employees!=null && employees.size()>0){
			for(Employee e:employees){
				System.out.println(e);
			}
			
		}
	}
	@Test
	public void testremoveEmpById(){
		boolean b=employeeService.removeEmpById(1L);
		System.out.println(b);
	}
	
	@Test(priority = 1)
	public void testSave(){
		Employee emp=new Employee("李四","销售",1000.00,DateUtil.createDate(2014, 10, 9));
		boolean bool=employeeService.registerOrEditEmp(emp);
		System.out.println(bool);
	}
	
	@Test(priority = 11)
	public void testSave1(){
		Employee emp=new Employee("李四","销售",6000.00,DateUtil.createDate(2014, 10, 9));
		emp.setId(1L);
		boolean bool=employeeService.registerOrEditEmp(emp);
		System.out.println(bool);
	}
	
	@Test(priority = 2)
	public void testRegister(){
		Employee emp=new Employee("rose","销售",10000,DateUtil.createDate(2013, 11, 9));		
		boolean bool=employeeService.registerOrEditEmp(emp);
		System.out.println(bool);
	}
	
	@Test(priority = 3)
	public void testFindById(){
		Employee emp=employeeService.findById(1L);
		System.out.println(emp);
	}
	
	@Test(priority = 4)
	public void testselectByName(){
		List<Employee> emps=employeeService.findByName("r");
		for(Employee e:emps){
			System.out.println(e);
		}
	}
	
	@Test(priority = 5)
	public void testselectByTitle(){
		List<Employee> emps=employeeService.findByTitle("经理");
		for(Employee e:emps){
			System.out.println(e);
		}
	}
	
	@Test(priority = 6)
	public void testselectBySalary(){
		List<Employee> emps=employeeService.findBySalary(6000, 8000);
		for(Employee e:emps){
			System.out.println(e);
		}
	}
	
	@Test(priority = 7)
	public void testselectByHiredate(){
		List<Employee> emps=employeeService.findByHireDate(DateUtil.createDate(2016, 11, 1), DateUtil.createDate(2016, 12, 31));
		for(Employee e:emps){
			System.out.println(e);
		}
	}
	
	@Test(priority = 8)
	public void testgetRowCount(){
		long count=employeeService.getRowCount();
		System.out.println(count);
	}
	
	@Test(priority = 9)
	public void testselectByPage(){
		List<Employee> emps=employeeService.findByPage(1, 5);
		for(Employee e:emps){
			System.out.println(e);
		}
	}
	
	@Test(priority = 10)
	public void testUpdateById(){
		Employee emp=employeeService.editSalaryById(27L, 8000);
		System.out.println(emp);
	}
	
}
