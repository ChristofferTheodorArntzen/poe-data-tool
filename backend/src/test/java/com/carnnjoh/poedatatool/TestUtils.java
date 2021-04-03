package com.carnnjoh.poedatatool;

import com.carnnjoh.poedatatool.model.Item;
import com.carnnjoh.poedatatool.model.PrivateStashTab;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Scanner;

public class TestUtils {

	private final static Logger LOGGER = LoggerFactory.getLogger(TestUtils.class);

	public static PrivateStashTab getPrivateStashTabFromResources(String fileName, ObjectMapper mapper) {
		File file = getFileFromResourcesAsURI(fileName);
		try {
			return mapper.readValue(file, PrivateStashTab.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Item getItemFromResources(String fileName, ObjectMapper mapper) {
		File file = getFileFromResourcesAsURI(fileName);
		try {
			return mapper.readValue(file, Item.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	//TODO: Try to make a generic type of mapping json from file
//	public static <E> E superDuperCoolStuff(String fileName, ObjectMapper mapper, Class<E> cas) {
//		try {
//			URI fileURI = getFileFromResourcesAsURI(fileName);
//			return mapper.readValue(new File(fileURI), );
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//
//	}

	public static File getFileFromResourcesAsURI(String fileName) {
		try {
			return new File(TestUtils.class.getResource(fileName).toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*this removes whitespace from data in file ...  */
	public static String getFileFromResourcesAsString(String fileName) {
		Scanner scanner = new Scanner(TestUtils.class.getResourceAsStream(fileName));
		StringBuilder sb = new StringBuilder();

		while(scanner.hasNext()) {
			sb.append(scanner.next());
		}

		System.out.println(sb.toString());

		return sb.toString();
	}

	public static <T> T getFileFromResourcesAndMapToObject(String name, ObjectMapper mapper, Class<?> target) {

		Scanner scanner = new Scanner(TestUtils.class.getResourceAsStream(name));
		StringBuilder sb = new StringBuilder();

		while(scanner.hasNext()) {
			sb.append(scanner.next());
		}

		String json = sb.toString();

		try {

			return mapper.readValue(json, mapper.getTypeFactory().constructType(Class.forName(target.getName())));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
