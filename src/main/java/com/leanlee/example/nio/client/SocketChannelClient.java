package com.leanlee.example.nio.client;

import com.leanlee.example.nio.server.Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SocketChannelClient {
	public void run() throws IOException {
		Selector selector = Selector.open(); // 打开选择器
		SocketChannel socketChannel = SocketChannel.open(); // 打开通道
		socketChannel.configureBlocking(false); // 配置非阻塞模型
		socketChannel.connect(new InetSocketAddress("127.0.0.1", Server.PORT)); // 连接远程主机
		socketChannel.register(selector, SelectionKey.OP_CONNECT | SelectionKey.OP_READ); // 注册事件

		while (true) { // 循环处理
			selector.select();
			Set<SelectionKey> keys = selector.selectedKeys();
			Iterator<SelectionKey> iter = keys.iterator();
			while (iter.hasNext()) {
				SelectionKey key = iter.next();
				if (key.isConnectable()) {
					SocketChannel channel = (SocketChannel) key.channel(); // 连接建立或者连接建立不成功
					if (channel.finishConnect()) { //完成连接的建立
					}
				}

				if (key.isReadable()) {
					SocketChannel channel = (SocketChannel) key.channel();
					ByteBuffer buffer = ByteBuffer.allocate(500 * 1024 * 1024);
					buffer.clear();
					channel.read(buffer);
					//buffer Handler
				}
				iter.remove();
			}
		}
	}
}
