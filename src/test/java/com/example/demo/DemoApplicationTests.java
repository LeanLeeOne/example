package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	Apple apple;

	@Test
	void contextLoads() {
		apple.print();
		apple.p1();
		apple.p2();
	}

}
