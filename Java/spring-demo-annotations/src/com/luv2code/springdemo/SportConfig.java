package com.luv2code.springdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@ComponentScan("com.luv2code.springdemo")
public class SportConfig {
	
	//define bean for our sad fortune service
	@Bean
	public FortuneService sadFortuneService() {
		return new SadFortuneService();
	}
//	
	@Bean
	public Coach swimCoach() {
		return new SwimCoach(sadFortuneService());
	}
//	@Bean
//	public FortuneService randomFortuneService() {
//		return new RandomFortuneService();
//	}
//	
//	//define bean for our swim coach and inject dependency
//	@Bean
//	public Coach swimCoach() {
//		return new SwimCoach(sadFortuneService());
//	}
//	
//	@Bean
//	@Lazy
//	public Coach tennisCoach() {
//		return new TennisCoach(sadFortuneService());
//	}
//	
	
	
}
