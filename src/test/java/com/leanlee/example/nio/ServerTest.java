package com.leanlee.example.nio;

import com.leanlee.example.nio.server.Cyc2018Server;
import com.leanlee.example.nio.server.HufeifeiServer;
import com.leanlee.example.nio.server.Server;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class ServerTest {

	@Test
	void hufeifei() throws IOException {
		Server server = new HufeifeiServer();
		server.run();
	}

	@Test
	void cyc2018() throws IOException {
		Server server = new Cyc2018Server();
		server.run();
	}
}
