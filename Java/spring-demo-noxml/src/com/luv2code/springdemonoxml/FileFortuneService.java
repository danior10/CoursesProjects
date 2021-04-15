package com.luv2code.springdemonoxml;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@Component
@Lazy
public class FileFortuneService implements FortuneService {
	
	private String fileName = "D:\\2 Java\\4 Spring\\1 Projekty\\spring-demo-annotations\\src\\fortune.properties";
	private List<String> theFortunes;
	private Random random = new Random();
	
	public FileFortuneService() {
		
		
	}

	@PostConstruct
	public void initializationMethod() {
		System.out.println("Inside @PostConstruct method");
		File theFile = new File(fileName);
		theFortunes = new ArrayList<>();
		
		System.out.println("Reading fortunes from the file: " + theFile);
		System.out.println("File exists: " + theFile.exists());
	
		try (BufferedReader br = new BufferedReader(
				new FileReader(theFile))) {
			
			String tempLine;
			while((tempLine = br.readLine()) != null) {
				theFortunes.add(tempLine);
			}
			
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String getFortune() {
		int index = random.nextInt(theFortunes.size());
		
		String tempFortune = theFortunes.get(index);
		
		return tempFortune;
	}

}
