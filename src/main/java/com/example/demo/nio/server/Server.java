package com.example.demo.nio.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public abstract class Server {
	protected Selector selector;
	protected ServerSocketChannel serverSocketChannel;
	protected ByteBuffer buffer;


	public void init() throws IOException {
		selector = Selector.open();
		serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
		buffer = ByteBuffer.allocate(1024);
	}

	public void run() throws IOException {
		init();
		bind();
		while (true) {
			selector.select();
			Set<SelectionKey> selectedKeys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = selectedKeys.iterator();

			while (iterator.hasNext()) {
				SelectionKey key = iterator.next();
				iterator.remove();

				if (key.isAcceptable()) {
					onAccept(key);
				}
				if (key.isReadable()) {
					onRead(buffer, key);
				}
				if (key.isConnectable()) {
					onConnect(key);
				}
			}
		}
	}


	public abstract void bind() throws IOException;

	public void onAccept(SelectionKey key) throws IOException {
		ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();

		// 服务器会为每个新连接创建一个 SocketChannel
		SocketChannel socketChannel = serverSocketChannel.accept();
		socketChannel.configureBlocking(false);

		// 这个新连接主要用于从客户端读取数据
		socketChannel.register(key.selector(), SelectionKey.OP_READ);
	}

	public void onConnect(SelectionKey key) throws IOException {
		SocketChannel socketChannel = (SocketChannel) key.channel();
		if (socketChannel.isConnectionPending()) {
			socketChannel.finishConnect();
		}
		socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
	}

	public void onRead(ByteBuffer buffer, SelectionKey key) throws IOException {
	}
}
