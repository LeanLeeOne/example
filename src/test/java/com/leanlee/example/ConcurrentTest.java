package com.leanlee.example;

import com.leanlee.example.concurrent.Hydra;
import com.leanlee.example.concurrent.WaitAndNotify;
import org.junit.jupiter.api.Test;

public class ConcurrentTest {
	static ThreadLocal<String> threadLocalUser = new ThreadLocal<>();

	@Test
	void hydra() {
		Hydra hydra = new Hydra();
		hydra.run();
	}

	@Test
	void waitAndNotify() throws InterruptedException {
		Object lock = new Object();
		Thread threadA = new Thread(new WaitAndNotify.RunA(lock));
//		threadA.start();
		Thread threadB = new Thread(new WaitAndNotify.RunB(lock, "B1"));
		threadB.start();
		threadB = new Thread(new WaitAndNotify.RunB(lock, "B2"));
		threadB.start();
		Thread threadC = new Thread(new WaitAndNotify.RunC(lock));
		threadC.start();
		Thread threadD = new Thread(new WaitAndNotify.RunD(lock));
		threadD.start();
		Thread.sleep(10000L);
	}

}
