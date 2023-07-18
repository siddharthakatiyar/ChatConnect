package com.chatconnect.backend.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

import com.chatconnect.backend.controllers.CCWebSocketHandler;
import com.chatconnect.backend.security.jwt.WebSocketAuthInterceptor;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new CCWebSocketHandler(), "/chat").setAllowedOrigins("*")
                .addInterceptors(new WebSocketAuthInterceptor());
    }
}

