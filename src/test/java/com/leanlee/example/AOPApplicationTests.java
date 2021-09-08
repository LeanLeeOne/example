package com.leanlee.example;

import com.leanlee.example.aop.Man;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AOPApplicationTests {

	@Autowired
	Man man;

	@Test
	void contextLoads() {
		man.say();
		man.eat();
		man.fuck();
	}

}
