package com.leanlee.example;

@Aspect2
public class SweetFruit implements Fruit {

	@Override
	public void print() {
		System.out.println("--SweetFruit");
	}

	public void p1() {
		System.out.println("--I am sweet");
	}
}
