package com.leanlee.example.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;

public class Stream {

	public void run() {
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
}
