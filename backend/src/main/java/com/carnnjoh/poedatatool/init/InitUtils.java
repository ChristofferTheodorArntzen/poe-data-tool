package com.carnnjoh.poedatatool.init;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URISyntaxException;

public class InitUtils {

	private final static Logger LOGGER = LoggerFactory.getLogger(InitUtils.class);
	private final static ObjectMapper mapper = new ObjectMapper();

	public static <E> E getFileFromResourcesAsObject(String fileName, Class<E> clazz) {
		try {
			return mapper.readValue(getFileFromResources(fileName), clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static File getFileFromResources(String fileName) {
		try {
			return new File(InitUtils.class.getResource(fileName).toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

}
