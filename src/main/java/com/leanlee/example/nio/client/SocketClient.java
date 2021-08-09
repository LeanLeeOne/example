package com.leanlee.example.nio.client;

import com.leanlee.example.nio.server.Server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class SocketClient {
	final static String MESSAGE_FORMAT = "No.%s %s";
	private int order;

	public SocketClient(int order){
		this.order = order;
	}

	public void run()throws IOException, InterruptedException{
		Socket socket = new Socket("127.0.0.1", Server.PORT); // 直接使用Socket会令服务端陷入Read事件的循环。
		OutputStream out = socket.getOutputStream();
		ZonedDateTime zdt = ZonedDateTime.now();
		var formatter = DateTimeFormatter.ofPattern("mm:ss.SSS");

		while (true) {
			String s = String.format(MESSAGE_FORMAT, order, formatter.format(zdt));
			out.write(s.getBytes());
			Thread.sleep(3000L);
		}
	}
}
