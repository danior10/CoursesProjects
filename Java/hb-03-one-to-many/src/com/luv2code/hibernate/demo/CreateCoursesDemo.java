package com.luv2code.hibernate.demo;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;


public class CreateCoursesDemo {

	public static void main(String[] args) {

		//create session factory
		SessionFactory factory = new Configuration()
								.configure("hibernate.cfg.xml")
								.addAnnotatedClass(Instructor.class)
								.addAnnotatedClass(InstructorDetail.class)
								.addAnnotatedClass(Course.class)
								.buildSessionFactory();

		//create session
		Session session = factory.getCurrentSession();
		
		try {
		
			session.beginTransaction();
			//get the instructor from db
			// create some courses
			//add courses to instructor
			//save the courses
			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theId);
			Course tempCourse1 = new Course("Air Guitar - The Ultimate Guide");
			Course tempCourse2 = new Course("The Pinball Masterclass");
			
			tempInstructor.add(tempCourse1);
			tempInstructor.add(tempCourse2);
			List<Course> tempCourses = tempInstructor.getCourses();
			for (Course course : tempCourses) {
				System.out.println(course.getTitle());
			}
			if ((tempCourse1.getInstructor()) == null) {
				System.out.println("No instructor connected");
				
			}else {
				System.out.println(tempCourse1.getInstructor());
			}
			if ((tempCourse2.getInstructor()) == null) {
				System.out.println("No instructor connected");
				
			}else {
				System.out.println(tempCourse2.getInstructor());
			}
			
			
			session.save(tempCourse1);
			session.save(tempCourse2);
			
			
			//commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
			
		}finally {
			session.close();
			factory.close();
		}
	
	}

}
