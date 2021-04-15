package com.luv2code.springdemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomFortune implements FortuneService {

	private List<String> fortunes;
	private Random random = new Random();
	
	public RandomFortune() {
		this.fortunes= new ArrayList<>();		
		fortunes.add("You'll win");
		fortunes.add("You'll lose");
		fortunes.add("You'll draw");
		
		
	}
	@Override
	public String getFortune() {
		return fortunes.get(random.nextInt(3));
	}

}
