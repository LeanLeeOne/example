package com.leanlee.example.nio;

import com.leanlee.example.nio.client.SocketChannelClient;
import com.leanlee.example.nio.client.SocketClient;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ClientTest {

	@Test
	void client0() throws IOException, InterruptedException {
		SocketClient client = new SocketClient(0);
		client.run();
	}

	@Test
	void client1() throws IOException, InterruptedException {
		SocketClient client = new SocketClient(1);
		client.run();
	}

	@Test
	void socketChannelClient() throws IOException {
		SocketChannelClient client = new SocketChannelClient();
		client.run();
	}
}
