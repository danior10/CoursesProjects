package com.luv2code.springboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springboot.cruddemo.entity.Employee;


@Repository
public class EmployeeDAOHibernateImpl implements EmployeeDAO {

	//define field for entitymanager
	
	private EntityManager entityManager;
	
	// set up constructor injection
	
	@Autowired
	public EmployeeDAOHibernateImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
		
	
	@Override
	public List<Employee> findAll() {
		
		//get the current hibernate session
		Session currentSesion = entityManager.unwrap(Session.class);
		
		// create a query
		Query<Employee> theQuery = currentSesion.createQuery("from Employee", Employee.class);
		
		// execute query and het result list
		List<Employee> employees = theQuery.getResultList();
		
		//return the results
		return employees;
		
	}


	@Override
	public Employee findById(int theId) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		Employee theEmployee = currentSession.get(Employee.class, theId);

		return theEmployee;
	}


	@Override
	public void save(Employee theEmployee) {
		
		Session currentSession = entityManager.unwrap(Session.class);
		
		currentSession.saveOrUpdate(theEmployee);
		
		
	}


	@Override
	public void deleteById(int theId) {

		Session currentSession = entityManager.unwrap(Session.class);
		
		Query theQuery = currentSession.createQuery("delete from Employee where id=:employeeId");
		theQuery.setParameter("employeeId", theId);
		theQuery.executeUpdate();
		
	}


}





























