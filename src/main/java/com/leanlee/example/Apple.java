package com.leanlee.example;

import org.springframework.stereotype.Component;

@Aspect1
@Component
public class Apple extends SweetFruit {

	@Override
	public void print() {
		System.out.println("--Apple");
	}

	@Aspect1
	public void p2() {
		System.out.println("--I am an apple.");
	}
}
