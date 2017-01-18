package com.tz.emp.dao.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.tz.emp.dao.IEmployeeDao;
import com.tz.entity.Employee;
import com.tz.entity.PageBean;
import com.tz.util.HibernateTemplates;
import com.tz.util.IHibernateCallBack;

public class EmployeeDaoImpl implements IEmployeeDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> selectAllEmps() {
		return (List<Employee>) HibernateTemplates.execute(new IHibernateCallBack() {
			
			@Override
			public Object execute(Session ses) throws HibernateException {
				return ses.createQuery("from Employee").list();
			}
		}) ;
	}

	@Override
	public boolean deleteEmpById(final Long id) {
		return (boolean) HibernateTemplates.execute(new IHibernateCallBack() {
			@Override
			public Object execute(Session ses) throws HibernateException {
				boolean bool = false;
				Employee employee = (Employee) ses.get(Employee.class, id);
				if (employee != null) {
					ses.delete(employee);
					bool = true;
				}
				return bool;
			}
		});
	}

	@Override
	public boolean saveOrUpdateEmp(final Employee emp) {
		try{
			return (boolean) HibernateTemplates.execute(new IHibernateCallBack() {
				
				@Override
				public Object execute(Session ses) throws HibernateException {
					if(emp!=null){
						ses.saveOrUpdate(emp);
						return true;
					}else{
						return false;
					}
				}
			});
		}catch(HibernateException e){
			return false;
		}
	}

	@Override
	public Employee selectById(final Long id) {
		return (Employee) HibernateTemplates.execute(new IHibernateCallBack() {
			
			@Override
			public Object execute(Session ses) throws HibernateException {
				return ses.get(Employee.class, id);
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> selectByName(final String name) {
		return (List<Employee>) HibernateTemplates.execute(new IHibernateCallBack() {
			
			@Override
			public Object execute(Session ses) throws HibernateException {
				return ses.createQuery("from Employee e where e.name like:name").setString("name", "%"+name+"%").list();
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> selectByTitle(final String title) {
		return (List<Employee>) HibernateTemplates.execute(new IHibernateCallBack() {
			
			@Override
			public Object execute(Session ses) throws HibernateException {
				return ses.createQuery("from Employee e where e.title=:title").setString("title", title).list();
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> selectBySalary(final double min, final double max) {
		return (List<Employee>) HibernateTemplates.execute(new IHibernateCallBack() {
			
			@Override
			public Object execute(Session ses) throws HibernateException {
				Criteria c=ses.createCriteria(Employee.class,"e");
				if(min>0){
					c.add(Restrictions.ge("e.salary", min));
				}
				if(max>0&&max>min){
					c.add(Restrictions.le("e.salary", max));
				}
				c.addOrder(org.hibernate.criterion.Order.asc("e.id"));
				return c.list();
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> selectByHireDate(final Date begin, final Date end) {
		return (List<Employee>) HibernateTemplates.execute(new IHibernateCallBack() {
			
			@Override
			public Object execute(Session ses) throws HibernateException {
				Criteria c=ses.createCriteria(Employee.class,"e");
				if(begin!=null){
					c.add(Restrictions.ge("e.hiredate", begin));
				}
				if(end!=null){
					c.add(Restrictions.le("e.hiredate", end));
				}
				c.addOrder(org.hibernate.criterion.Order.asc("e.hiredate"));
				return c.list();
			}
		});
	}

	@Override
	public boolean deleteEmpByIds(final Long[] ids) {
		return (boolean) HibernateTemplates.execute(new IHibernateCallBack() {
			
			@Override
			public Object execute(Session ses) throws HibernateException {
//				String hql="";
//				if(ids!=null&&ids.length>0){
//					for(int i=0;i<ids.length;i++){
//						if(i==0){
//							hql="id="+Long.parseLong(ids[i]);
//						}else{
//							hql=hql+"or id="+Long.parseLong(ids[i]);
//						}
//					}
//					ses.createQuery("delete from Employee where"+hql).executeUpdate();
//					return true;
//				}else{
//					return false;
//				}
				if(ids!=null&&ids.length>0){
					List<Long> idsList=Arrays.asList(ids);
					ses.createQuery("delete from Employee where id in (:ids)")
					.setParameterList("ids", idsList).executeUpdate();
					return true;
				}else{
					return false;
				}
				
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> selectByPage(final int page, final int pageSize) {
		return (List<Employee>) HibernateTemplates.execute(new IHibernateCallBack() {
			
			@Override
			public Object execute(Session ses) throws HibernateException {
				return ses.createQuery("from Employee").setFirstResult((page-1)*pageSize).setMaxResults(pageSize).list();
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageBean selectEmpsByPage(final int pageNow,final int pageSize) {
		PageBean pageBean=new PageBean();
		List<Employee> employees=(List<Employee>) HibernateTemplates.execute(new IHibernateCallBack() {
			
			@Override
			public Object execute(Session ses) throws HibernateException {
				return ses.createQuery("from Employee").setFirstResult((pageNow-1)*pageSize).setMaxResults(pageSize).list();
			}
		});
		pageBean.setPageNow(pageNow);
		pageBean.setPageSize(pageSize);
		pageBean.setEmployees(employees);
		int rowCount=(int)getRowCount();
		pageBean.setRowCount(rowCount);
		int pageCount=0;
		if(rowCount%pageSize==0){
			pageCount=rowCount/pageSize;
		}else{
			pageCount=rowCount/pageSize+1;
		}
		pageBean.setPageCount(pageCount);
		return pageBean;
	}

	private long getRowCount() {
		return (long) HibernateTemplates.execute(new IHibernateCallBack() {
			@Override
			public Object execute(Session ses) throws HibernateException {
				return ses.createQuery("select count(*) from Employee").uniqueResult();
			}
		});
	}

	@Override
	public Employee updateSalaryById(final Long id, final double salary) {
		return (Employee) HibernateTemplates.execute(new IHibernateCallBack() {
			
			@Override
			public Object execute(Session ses) throws HibernateException {
				Employee employee=(Employee) ses.get(Employee.class, id);
				if(employee!=null){
					employee.setSalary(salary);
					return employee;
				}else{
					return null;
				}
			}
		});
	}
	

}
