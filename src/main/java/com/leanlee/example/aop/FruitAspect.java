package com.leanlee.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class FruitAspect {

	@Before("within(com.leanlee.example.aop.SweetFruit)")
	void within() {
		System.out.println("within");
	}

	@Before("target(f)")
	void target(JoinPoint joinPoint, Fruit f) {
		System.out.println("target");
	}

	@Before("@within(com.leanlee.example.aop.Aspect2)")
	void atWithin() {
		System.out.println("@within");
	}

	@Before("within(com.example.demo.*) && (@target(com.leanlee.example.aop.Aspect1))")
	void atTarget() {
		System.out.println("@target");
	}

	@Before("@annotation(a)")
	void atAnnotation(JoinPoint joinPoint, Aspect1 a) {
		System.out.println("@annotation");
	}

}