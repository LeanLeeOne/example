package com.leanlee.example.nio.server;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public abstract class Server {
	public static final int PORT = 8888;

	protected Selector selector;
	protected ServerSocketChannel serverSocketChannel;

	protected void init() throws IOException {
		selector = Selector.open();
		serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(false);
		serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT); // 阅读源码可知，ServerSocketChannel只允许注册Accept事件。
	}

	public void run() throws IOException {
		init();
		bind();
		while (true) {
			int select = selector.select();
			Set<SelectionKey> keys = selector.selectedKeys();
			Iterator<SelectionKey> iterator = keys.iterator();

			while (iterator.hasNext()) {
				SelectionKey key = iterator.next();
				iterator.remove();

				if (key.isAcceptable()) {
					onAccept(key);
				}
				if (key.isConnectable()) {
					onConnect(key);
				}
				if (key.isReadable()) {
					ByteBuffer buffer = ByteBuffer.allocate(1024);
					key.attach(buffer);
					onRead(key);
				}
				if(key.isWritable()){
					onWrite(key);
				}
			}
		}
	}


	public abstract void bind() throws IOException;

	public void onAccept(SelectionKey key) throws IOException {
		ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
		SocketChannel socketChannel = serverSocketChannel.accept(); // 服务器会为每个新连接创建一个 SocketChannel
		socketChannel.configureBlocking(false); // 设置为非阻塞
		socketChannel.register(key.selector(), SelectionKey.OP_READ); // 这个新连接主要用于从客户端读取数据
	}

	// todo 服务端不会触发Connect事件
	public void onConnect(SelectionKey key) throws IOException {
		SocketChannel socketChannel = (SocketChannel) key.channel();
		if (socketChannel.isConnectionPending()) {
			socketChannel.finishConnect();
		}
		socketChannel.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
	}

	public void onRead(SelectionKey key) throws IOException {
	}

	public void onWrite(SelectionKey key) throws IOException {
		SocketChannel channel = (SocketChannel) key.channel();
		ByteBuffer buffer = (ByteBuffer) key.attachment();

		key.interestOps(key.interestOps() & ~SelectionKey.OP_WRITE); // 注销写事件
	}
}
