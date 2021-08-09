package com.leanlee.example.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class HufeifeiServer extends Server {

	private static final String CLOSE_ACTION = "close";

	@Override
	public void bind() throws IOException {
		serverSocketChannel.bind(new InetSocketAddress(Server.PORT));
	}

	@Override
	public void onRead(SelectionKey key) throws IOException {
		SocketChannel channel = (SocketChannel) key.channel();
		ByteBuffer buffer = (ByteBuffer) key.attachment();

		channel.read(buffer);
		String receiveString = new String(buffer.array());
		if (CLOSE_ACTION.equals(receiveString.trim())) {
			channel.close();
			System.out.println("Not accepting client messages anymore");
		} else {
			System.out.println(receiveString);
			buffer.flip();
			channel.write(buffer);
			buffer.clear();
		}
	}

}