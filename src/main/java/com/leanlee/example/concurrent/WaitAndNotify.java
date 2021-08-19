package com.leanlee.example.concurrent;

public class WaitAndNotify {
	public static class RunA implements Runnable {

		private Object lock;

		public RunA(Object lock) {
			this.lock = lock;
		}

		@Override
		public void run() {
			synchronized (lock) {
				try {
					System.out.println("A begin");
					Thread.sleep(2000);
					System.out.println("A end");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static class RunB implements Runnable {
		private String name;
		private Object lock;

		public RunB(Object lock, String name) {
			this.lock = lock;
			this.name = name;
		}

		@Override
		public void run() {
			synchronized (lock) {
				try {
					System.out.println(name + " wait");
					lock.wait();
					System.out.println(name + " wakeup");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static class RunC implements Runnable {

		private Object lock;

		public RunC(Object lock) {
			this.lock = lock;
		}

		@Override
		public void run() {
			synchronized (lock) {
				try {
					System.out.println("C run");
					lock.notifyAll();
					Thread.sleep(1000L);
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static class RunD implements Runnable {

		private Object lock;

		public RunD(Object lock) {
			this.lock = lock;
		}

		@Override
		public void run() {
			synchronized (lock) {
				System.out.println("D run");
			}
		}
	}
}
