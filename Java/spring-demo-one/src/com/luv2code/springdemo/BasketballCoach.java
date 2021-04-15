package com.luv2code.springdemo;

public class BasketballCoach implements Coach{
	
	private FortuneService fortuneService;

	public BasketballCoach(FortuneService theFortuneService) {
		fortuneService = theFortuneService;
	}

	@Override
	public String getDailyWorkout() {
		return "Train bouncing for 30 minutes";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();

	}
	
	

}
