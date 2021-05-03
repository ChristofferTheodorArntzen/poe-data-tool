package com.carnnjoh.poedatatool.db.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.support.KeyHolder;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Utils {

	public static <T> T readObject(String json, ObjectMapper mapper) {
		try {
			return mapper.readValue(json, new TypeReference<T>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <T> T tryGet(GetAction<T> block) {
		try {
			return block.get();
		} catch (EmptyResultDataAccessException erdae){
			// do nothing, happens when the application tries to fetch an entity with a PK that does not exist.
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}

	public static Result tryUpdate(UpdateAction block) {
		try {
			return block.run();
		}catch (Exception e){
			e.printStackTrace();
		}
		return new FailedResult();
	}

	public static Result getCreateResult(KeyHolder keyHolder){

		if(keyHolder.getKey() != null){
			return new CreateSuccessResult(keyHolder.getKey().intValue());
		} else {
			return new FailedResult();
		}
	}

	public static Result getCreateResultList(List<Integer> keys) {

		if(keys != null && keys.size() > 0) {
			return new CreateSuccessResultList(
					keys.stream()
							.map(CreateSuccessResult::new)
							.collect(Collectors.toList())
			);
		} else {
			return new FailedResult();
		}
	}
}

