package com.leanlee.example.aop;

import org.springframework.stereotype.Component;

@AnnotationMan
@Component
public class Man extends Human {

	@Override
	public void say() {
		System.out.println("Man.say()");
		System.out.println("---------------------------------------");
	}

	//	@AnnotationMan
	public void fuck() {
		System.out.println("Man.fuck()");
		System.out.println("---------------------------------------");
	}
}
