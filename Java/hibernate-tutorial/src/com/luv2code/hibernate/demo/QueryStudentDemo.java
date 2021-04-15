package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Student;

public class QueryStudentDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Student.class)
								.buildSessionFactory();

		//create session
		Session session = factory.getCurrentSession();
		
		try {
			
			//start a transaction
			session.beginTransaction();
			
			//query students
			List<Student> theStudents = session.createQuery("from Student").list();

			//display the students
			displayStudents(theStudents);
			
			//query students: last name="Doe"
			theStudents = session.createQuery("from Student s where s.lastName='Doe'").getResultList();
			
			//display the students
			System.out.println("\n\nStudents who have last name of Doe");
			displayStudents(theStudents);
			
			// query students: last name='Doe' OR firstName='Daffy'
			theStudents = 
					session.createQuery("from Student s where"
							+ " s.lastName='Doe' OR firstName='Daffy'").getResultList();
			System.out.println("\n\nStudnets who havae last name of Doe OR first name of Daffy");
			displayStudents(theStudents);
			
			//query Students where email like '%luv2code.com'
			theStudents = session.createQuery("from Student s where"
					+ " s.email like '%luv2code.com'").getResultList();
			System.out.println("\n\nStudents with email like 'luv2code.com'");
			displayStudents(theStudents);
			
			//query students with gmail
			theStudents = session.createQuery("from Student s where s.email like '%gmail.com'").getResultList();
//			displayStudents(theStudents);

			if(theStudents.isEmpty()) {
				System.out.println("\n\nThere is no student with that email");
			}else {
				displayStudents(theStudents);
			}
			
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
			
		}finally {
			factory.close();
		}
	
	}

	private static void displayStudents(List<Student> theStudents) {
		for (Student s:theStudents) {
			System.out.println(s);
		}
	}

}
