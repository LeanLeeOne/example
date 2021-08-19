package com.leanlee.example.concurrent;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Hydra {
	private ExecutorService executorService;
	float f = 1.1f;

	public Hydra() {
		executorService = Executors.newFixedThreadPool(3);
	}

	public void temp() {
		FileOutputStream stream;
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		{
			try {
				stream = new FileOutputStream("");
				FileChannel fileChannel = stream.getChannel();
				fileChannel.read(buffer);
				ServerSocketChannel socketChannel = null;
				socketChannel.configureBlocking(false);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	private class Task implements Callable<String> {
		@Override
		public String call() throws Exception {
			String result = "0";
			for (int i = 0; i < 99999; i++) {
				result = Integer.valueOf(result) + i + "";
			}
			return result;
		}
	}

	public void run() {
		Thread thread = new Thread();
		thread.interrupt();
		thread.isInterrupted();

		Callable<String> task = new Task();
		Future<String> future = executorService.submit(task);
		boolean done = future.isDone();
	}
}
