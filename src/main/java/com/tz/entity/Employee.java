package com.tz.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.tz.util.DateUtil;

@Entity
@Table(name="SERVLET_EMPLOYEE")
public class Employee {
	private Long id;
	private String name;
	private String title;
	private double salary;
	private Date hiredate;

	public Employee() {

	}

	public Employee(String name, String title, double salary, Date hiredate) {
		super();
		this.name = name;
		this.title = title;
		this.salary = salary;
		this.hiredate = hiredate;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO,generator="emp_id")
	@SequenceGenerator(name="emp_id",sequenceName="servlet_emp_id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(nullable=false,unique=true)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column
	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Column
	@Temporal(TemporalType.DATE)
	public Date getHiredate() {
		return hiredate;
	}

	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}

	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((hiredate == null) ? 0 : hiredate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (hiredate == null) {
			if (other.hiredate != null)
				return false;
		} else if (!hiredate.equals(other.hiredate))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", title=" + title
				+ ", salary=" + salary + ", hiredate="
				+ DateUtil.formatDate(hiredate, "yyyy-MM-dd") + "]";
	}
}
