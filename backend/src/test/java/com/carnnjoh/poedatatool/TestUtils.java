package com.carnnjoh.poedatatool;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URISyntaxException;



public class TestUtils {

	private final static Logger LOGGER = LoggerFactory.getLogger(TestUtils.class);
	private final static ObjectMapper mapper = new ObjectMapper();

	public static <E> E getFileFromResourcesAsObject(String fileName, Class<E> clazz) {
		try {
			return mapper.readValue(getFileFromResourcesAsFile(fileName), clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static File getFileFromResourcesAsFile(String fileName) {
		try {
			return new File(TestUtils.class.getResource(fileName).toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

}
