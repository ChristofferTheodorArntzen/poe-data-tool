package com.carnnjoh.poedatatool.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketPublishService {

	public static final Logger LOGGER = LoggerFactory.getLogger(WebSocketPublishService.class);
	public static final String WEB_SOCKET_PATH = "/topic/greetings";

	@Autowired
	SimpMessagingTemplate simpMessagingTemplate;

	@Autowired
	ObjectMapper objectMapper;

	public void publishToWebSocket(Object objectToPublish) {
		publishToWebSocket(WEB_SOCKET_PATH, objectToPublish);
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
