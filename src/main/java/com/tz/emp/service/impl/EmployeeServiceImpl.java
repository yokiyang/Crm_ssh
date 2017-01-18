package com.tz.emp.service.impl;

import java.util.Date;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tz.emp.dao.IEmployeeDao;
import com.tz.emp.service.IEmployeeService;
import com.tz.entity.Employee;
import com.tz.entity.PageBean;

@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
public class EmployeeServiceImpl implements IEmployeeService {
	@Resource
	private IEmployeeDao employeeDao;
	@Resource
	private SessionFactory sessionFactory;

	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<Employee> findAllEmps() {
		return employeeDao.selectAllEmps();
	}

	@Override
	public boolean removeEmpById(Long id) {
		return employeeDao.deleteEmpById(id);
	}

	@Override
	public boolean registerOrEditEmp(Employee emp) {
		return employeeDao.saveOrUpdateEmp(emp);
	}

	@Override
	public Employee findById(Long id) {
		return employeeDao.selectById(id);
	}

	@Override
	public List<Employee> findByName(String name) {
		return employeeDao.selectByName(name);
	}

	@Override
	public List<Employee> findByTitle(String title) {
		return employeeDao.selectByTitle(title);
	}

	@Override
	public List<Employee> findBySalary(double min, double max) {
		return employeeDao.selectBySalary(min, max);
	}

	@Override
	public List<Employee> findByHireDate(Date begin, Date end) {
		return employeeDao.selectByHireDate(begin, end);
	}

	@Override
	public boolean removeEmpByIds(Long[] ids) {
		return employeeDao.deleteEmpByIds(ids);
	}

	@Override
	public long getRowCount() {
		return (long) getSession().createQuery("select count(*) from Employee")
				.uniqueResult();
	}

	@Override
	public List<Employee> findByPage(int page, int pageSize) {
		return employeeDao.selectByPage(page, pageSize);
	}

	@Override
	public PageBean findEmpsByPage(int pageNow, int pageSize) {
		return employeeDao.selectEmpsByPage(pageNow, pageSize);
	}

	@Override
	public Employee editSalaryById(Long id, double salary) {
		return employeeDao.updateSalaryById(id, salary);
	}
}
