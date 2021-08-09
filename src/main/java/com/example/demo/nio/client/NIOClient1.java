package com.example.demo.nio.client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class NIOClient1 {
	public static void main(String[] args) throws IOException, InterruptedException {
		Socket socket = new Socket("127.0.0.1", 8888);
		OutputStream out = socket.getOutputStream();
		ZonedDateTime zdt = ZonedDateTime.now();
		var formatter = DateTimeFormatter.ofPattern("mm:ss.SSS");

		while (true) {
			String s = "No.1 " + formatter.format(zdt);
			out.write(s.getBytes());
			Thread.sleep(3000L);
		}
	}
}
