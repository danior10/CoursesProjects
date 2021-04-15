package com.luv2code.springdemonoxml;

import org.springframework.stereotype.Component;

@Component
public class SadFortuneService implements FortuneService {

	@Override
	public String getFortune() {
		return "This is a sad day";
	}

}
