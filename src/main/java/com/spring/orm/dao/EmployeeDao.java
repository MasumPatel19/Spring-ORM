package com.spring.orm.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.orm.hibernate5.HibernateTemplate;

import com.spring.orm.entities.Employee;

public class EmployeeDao {

	private HibernateTemplate hibernateTemplate;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

//	insert employee record
	@javax.transaction.Transactional
	public int insert(Employee employee) {
		Integer insID = (Integer) hibernateTemplate.save(employee);
		return insID;
	}

//	get employee record
	public Employee getEmployee(int id) {
		Employee employee = hibernateTemplate.get(Employee.class, id);
		return employee;
	}

//	get all employee records
	public List<Employee> getAllEmploee() {
		List<Employee> allEmployee = hibernateTemplate.loadAll(Employee.class);
		return allEmployee;
	}

//	delete employee
	@Transactional
	public void deleteEmployee(int id) {
		Employee employee = hibernateTemplate.get(Employee.class, id);
		hibernateTemplate.delete(employee);
	}

//	update student
	@Transactional
	public void updateEmployee(Employee employee) {
		hibernateTemplate.update(employee);
	}

}
