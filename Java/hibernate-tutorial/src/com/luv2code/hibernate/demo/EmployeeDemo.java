package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Employee;

public class EmployeeDemo {
	
	private static SessionFactory factory = new Configuration()
											.configure("hibernate.cfg.xml")
											.addAnnotatedClass(Employee.class)
											.buildSessionFactory();

	private static Session session = factory.getCurrentSession();

	public static void main(String[] args) {


		
		try {
			
			addEmployee(new Employee("Daniel", "Rozwadowski","Prime"));
			addEmployee(new Employee("Mateusz", "Pajdzik","Prime"));
			addEmployee(new Employee("Rafal", "Suchanek","Prime"));
			addEmployee(new Employee("Sandra", "Stanislawska","Ejbisi"));
			addEmployee(new Employee("Piotr", "Flis","Mentor Graphics"));
			addEmployee(new Employee("Aleksandra", "Szoczek","Ambasada"));
			
			queryCompanyEmployee("Prime");
			getEmployee(1);
			deleteEmployee(3);
			
			
		}finally {
			factory.close();
		}
		
	}

	public static void addEmployee(Employee employee) {
		
		session = factory.getCurrentSession();
		session.beginTransaction();
		System.out.println("Adding new employee...");
		session.save(employee);
		session.getTransaction().commit();
		System.out.println("Done! xD");
		
		
	}

	public static Employee getEmployee(int id) {
		session = factory.getCurrentSession();
		session.beginTransaction();
		Employee tempEmployee = session.get(Employee.class, id);
		System.out.println(tempEmployee);
		session.getTransaction().commit();
		return tempEmployee;
	}

	public static List<Employee> queryCompanyEmployee(String company){
		
		session = factory.getCurrentSession();
		session.beginTransaction();
		List<Employee> employees = session.createQuery("from Employee e where e.company='" + company + "'").getResultList();
		for(Employee e:employees) {
			System.out.println(e);
		}
		session.getTransaction().commit();
		return employees;
	}

	public static void deleteEmployee(Employee employee) {
		session = factory.getCurrentSession();
		session.beginTransaction();
		System.out.println("Deleting employee...");
		session.delete(employee);
		session.getTransaction().commit();
		
	}
	
	public static void deleteEmployee(int id) {
		session = factory.getCurrentSession();
		session.beginTransaction();
		System.out.println("Deleting employee...");
		Employee tempEmployee = session.get(Employee.class, id);
		session.delete(tempEmployee);
		session.getTransaction().commit();
		
	}

}
