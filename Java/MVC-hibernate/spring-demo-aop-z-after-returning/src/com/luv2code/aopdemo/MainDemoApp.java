package com.luv2code.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.luv2code.aopdemo.dao.AccountDAO;
import com.luv2code.aopdemo.dao.MembershipDAO;

public class MainDemoApp {

	public static void main(String[] args) {
		
		//read spring config class
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// get the bean from spring container
		AccountDAO theAccountDAO = context.getBean("accountDAO",AccountDAO.class);
		
		//get membership bean from spring container
		MembershipDAO theMembeeshipDAO = context.getBean("membershipDAO", MembershipDAO.class);
		
		//call the accountdao getter/setter methods
		theAccountDAO.setName("foobar");
		theAccountDAO.setServiceCode("silver");
		
		String name = theAccountDAO.getName();
		String code = theAccountDAO.getServiceCode();
		
		//call the business method
		Account myAccount = new Account();
		myAccount.setName("Madhu");
		myAccount.setLevel("Platinum");
		
		theAccountDAO.addAccount(myAccount,true);
		theMembeeshipDAO.addSillyMember();
		theAccountDAO.doWork();
		theMembeeshipDAO.goToSleep();
		

		
		//close the context
		context.close();

	}

}