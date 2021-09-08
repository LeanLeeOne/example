package com.leanlee.example.aop;

@AnnotationHuman
public class Human implements Weight {

	private int weight;

	@Override
	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public int getWeight() {
		System.out.println("Human.weight: " + weight);
		System.out.println("---------------------------------------");
		return weight;
	}

	public void say() {
		System.out.println("Human.say()");
		System.out.println("---------------------------------------");
	}

	public void eat() {
		System.out.println("Human.eat()");
		System.out.println("---------------------------------------");
	}
}
