package com.leanlee.example.concurrent;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

public class Lock {

	public void run() throws InterruptedException {
		ReentrantLock lock = new ReentrantLock();

		lock.lock();
		lock.tryLock(1, TimeUnit.SECONDS);

		lock.newCondition();

		StampedLock lock1 = new StampedLock();
		lock1.tryOptimisticRead();
		AtomicInteger i = new AtomicInteger();
		i.incrementAndGet();
	}
}
