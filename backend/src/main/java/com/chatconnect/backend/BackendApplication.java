package com.chatconnect.backend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;

@SpringBootApplication
public class BackendApplication {

    @Value("${socket.host}")
	private String SOCKETHOST;
	@Value("${socket.port}")
	private int SOCKETPORT;

	@Bean
	public SocketIOServer socketIOServer() {
		Configuration config = new Configuration();
		config.setHostname(SOCKETHOST);
		config.setPort(SOCKETPORT);
		config.setOrigin("http://localhost:3000");
		return new SocketIOServer(config);
	}

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }
}
