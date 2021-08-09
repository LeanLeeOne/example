package com.leanlee.example.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class Cyc2018Server extends Server {

	@Override
	public void bind() throws IOException {
		ServerSocket serverSocket = serverSocketChannel.socket();
		serverSocket.bind(new InetSocketAddress(8888));
	}

	@Override
	public void onRead(SelectionKey key) throws IOException {
		SocketChannel channel = (SocketChannel) key.channel();
		ByteBuffer buffer = (ByteBuffer) key.attachment();

		while (true) { // todo 这个循环
			StringBuilder data = new StringBuilder();
			int n = channel.read(buffer);
			if (n == -1) {
				break;
			}
			buffer.flip();
			int limit = buffer.limit();
			char[] dst = new char[limit];
			for (int i = 0; i < limit; i++) {
				dst[i] = (char) buffer.get(i);
			}
			data.append(dst);
			System.out.println(data.toString());
			buffer.clear();
		}
		channel.close();
	}
}