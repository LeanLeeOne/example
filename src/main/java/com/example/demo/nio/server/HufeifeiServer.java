package com.example.demo.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class HufeifeiServer extends Server {

	private static final String CLOSE_ACTION = "close";

	public static void main(String[] args) throws IOException {
		Server server = new HufeifeiServer();
		server.run();
	}

	@Override
	public void bind() throws IOException {
		serverSocketChannel.bind(new InetSocketAddress(8888));
	}

	@Override
	public void onRead(SelectionKey key) throws IOException {
		SocketChannel channel = (SocketChannel) key.channel();
		ByteBuffer buffer = (ByteBuffer) key.attachment();

		channel.read(buffer);
		String receiveString = new String(buffer.array());
		System.out.println("Receive string: " + receiveString);
		if (CLOSE_ACTION.equals(receiveString.trim())) {
			channel.close();
			System.out.println("Not accepting client messages anymore");
		} else {
			buffer.flip();
			int limit = buffer.limit();
			char[] dst = new char[limit];
			for (int i = 0; i < limit; i++) {
				dst[i] = (char) buffer.get(i);
			}
			System.out.println(dst);
			buffer.clear();
		}
	}

}