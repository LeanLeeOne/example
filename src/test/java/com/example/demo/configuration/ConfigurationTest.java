package com.example.demo.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//@Import(MyConfiguration.class)
//@ComponentScan("com.example.demo.configuration")
@SpringBootTest
public class ConfigurationTest {

	@Autowired
	ApplicationContext applicationContext;

	@Test
	public void main() {
		applicationContext = new AnnotationConfigApplicationContext();
		((AnnotationConfigApplicationContext) applicationContext).register(MyConfiguration.class, MyConfiguration.class);
		MyConfiguration myConfiguration = applicationContext.getBean(MyConfiguration.class);
		Lemon lemon = applicationContext.getBean(Lemon.class);
		myConfiguration.equals(new Object());
	}
}
