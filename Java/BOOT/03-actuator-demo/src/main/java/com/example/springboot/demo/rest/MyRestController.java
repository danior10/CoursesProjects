package com.example.springboot.demo.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyRestController {

	
	@GetMapping("/")
	public String home() {
		return "Ssij pałe Ty jebany Spring Boot!!!";
	}
	
	
	// expose a new endpoint for "workout" 
	@GetMapping("/workout")
	public String getDailyWorkout() {
		return "Run a hard 5k";
	}
	
	@GetMapping("/fortune")
	public String getDailyFortune() {
		return "Today is your lucky day";
		
	}
	
	
}
