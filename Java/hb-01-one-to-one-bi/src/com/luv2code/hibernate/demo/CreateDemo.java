package com.luv2code.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;


public class CreateDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.buildSessionFactory();

		//create session
		Session session = factory.getCurrentSession();
		
		try {
/*
			//create objects
			Instructor tempInstructor = new Instructor("Chad", "Darby", "darby@luv2code.com");
			InstructorDetail tempInstructorDetail = new InstructorDetail("http://luv2code.com/youtube","Luv 2 code !!!");
			tempInstructor.setInstructorDetail(tempInstructorDetail);
	*/		 
			//associate the objects
		
			Instructor tempInstructor = new Instructor("Madhu", "Patel", "madhu@luv2code.com");
			InstructorDetail tempInstructorDetail = new InstructorDetail("http://youtube.com","Guitar");
			tempInstructor.setInstructorDetail(tempInstructorDetail);
			//start a transaction
			session.beginTransaction();
			System.out.println("Saving instuctor: " + tempInstructor);
			session.save(tempInstructor);
			
			
			//commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
			
			
		}finally {
			factory.close();
		}
	
	}

}
