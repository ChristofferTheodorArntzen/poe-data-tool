package com.carnnjoh.poedatatool.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import static com.carnnjoh.poedatatool.socket.WebSocketConstants.*;

@Service
public class WebSocketPublishService {

	public static final Logger LOGGER = LoggerFactory.getLogger(WebSocketPublishService.class);

	@Autowired
	SimpMessagingTemplate simpMessagingTemplate;

	@Autowired
	ObjectMapper objectMapper;

	public void publishToFeed(Object objectToPublish) {
		publishToWebSocket(WEB_SOCKET_FEED, objectToPublish);
	}

	public void publishToGenerator(Object objectToPublish) {
		publishToWebSocket(WEB_SOCKET_GENERATOR, objectToPublish);
	}

	public void publishToWebSocket(String path, Object objectToPublish) {
		try{
			simpMessagingTemplate.convertAndSend(path, objectMapper.writeValueAsString(objectToPublish));
		} catch (JsonProcessingException e) {
			LOGGER.warn(e.getMessage());
			e.printStackTrace();
		}
	}

}
