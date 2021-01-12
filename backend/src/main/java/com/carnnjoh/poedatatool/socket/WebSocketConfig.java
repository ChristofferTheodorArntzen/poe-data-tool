package com.carnnjoh.poedatatool.socket;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.messaging.*;


@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		registry.enableSimpleBroker("/topic");
		registry.setApplicationDestinationPrefixes("/app");
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/gs-guide-websocket")
				.setAllowedOrigins("http://localhost:3000").withSockJS();
	}

	@EventListener(SessionConnectEvent.class)
	public void handleWebSocketConnectListener(SessionConnectEvent event) {
		System.out.println("got a new connection" + event.getUser());
	}

	@EventListener(SessionDisconnectEvent.class)
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		System.out.println("session closed : " + event.getSessionId());
	}

	@EventListener(SessionSubscribeEvent.class)
	public void handleWebSocketSubscriptionListener(SessionSubscribeEvent event) {
		System.out.println("subscriptions: " + event.getUser());
	}
}
