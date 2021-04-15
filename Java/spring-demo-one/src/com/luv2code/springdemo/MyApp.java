package com.luv2code.springdemo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyApp {
	private static List<String> lista = new ArrayList<>();
	private static Random random = new Random();

	public static void main(String[] args) {


		lista.add("You'll win");
		lista.add("You'll lose");
		lista.add("You'll draw");
		System.out.println(random.nextInt(3));
		
		for(int i=0;i<lista.size();i++) {
			System.out.println(lista.get(i));
		}
//		Coach theCoach = new TrackCoach();
//		System.out.println(theCoach.getDailyWorkout());
		
	}

}
