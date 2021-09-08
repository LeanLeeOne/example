package com.leanlee.example.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class HumanAspect {

	@Before("within(com.leanlee.example.aop.Weight)")
	void withinWeight() {
		System.out.println("within, Weight");
	}

	@Before("target(weight)")
	void targetWeight(JoinPoint joinPoint, Weight weight) {
		System.out.println("target, Weight");
	}

	@Before("within(com.leanlee.example.aop.Human)")
	void withinHuman() {
		System.out.println("within, Human");
	}

	@Before("target(human)")
	void targetHuman(JoinPoint joinPoint, Human human) {
		System.out.println("target, Human");
	}

	@Before("within(com.leanlee.example.aop.Man)")
	void withinMan() {
		System.out.println("within, Man");
	}

	@Before("target(man)")
	void targetMan(JoinPoint joinPoint, Man man) {
		System.out.println("target, Man");
	}

	@Before("@within(aHuman)")
	void atWithinHuman(JoinPoint joinPoint, com.leanlee.example.aop.AnnotationHuman aHuman) {
		System.out.println("@within, AnnotationHuman");
	}

	/**
	 * 切点的条件过于宽泛就会包含带有final修饰的类，进而导致
	 * “AopConfigException: Could not generate CGLIB subclass of class [class org.springframework.boot.autoconfigure.AutoConfigurationPackages$BasePackages]: Common causes of this problem include using a final class or a non-visible class; nested exception is java.lang.IllegalArgumentException: Cannot subclass final class org.springframework.boot.autoconfigure.AutoConfigurationPackages$BasePackages”
	 * 此时需要使用“within”等指示器缩小范围。
	 */
	@Before("within(com.leanlee.example.aop.*) && @target(aHuman)")
	void atTargetHuman(JoinPoint joinPoint, com.leanlee.example.aop.AnnotationHuman aHuman) {
		System.out.println("@target, AnnotationHuman");
	}

	@Before("@within(aMan)")
	void atWithinMan(JoinPoint joinPoint, com.leanlee.example.aop.AnnotationMan aMan) {
		System.out.println("@within, AnnotationMan");
	}

	@Before("within(com.leanlee.example.aop.*) && @target(aMan)")
	void atTargetMan(JoinPoint joinPoint, com.leanlee.example.aop.AnnotationMan aMan) {
		System.out.println("@target, AnnotationMan");
	}

	@Before("@annotation(a)")
	void atAnnotation(JoinPoint joinPoint, AnnotationMan a) {
		System.out.println("@annotation");
	}

}
