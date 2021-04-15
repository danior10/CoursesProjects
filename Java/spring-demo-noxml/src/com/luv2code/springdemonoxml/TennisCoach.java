package com.luv2code.springdemonoxml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
//@Lazy
public class TennisCoach implements Coach {

	@Autowired
	@Qualifier("sadFortuneService")
	private FortuneService fortuneService;
	
	
	public TennisCoach() {
		System.out.println(">> Inside default constructor");
	}
	
	@Override
	public String getDailyWorkout() {
		return "Train your right hand volley";
	}

	public String getFortune() {
		return fortuneService.getFortune();
	}
}
