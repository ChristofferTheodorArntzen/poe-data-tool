package com.carnnjoh.poedatatool.db.utils;

@FunctionalInterface
public interface GetAction<T> {

	T get() throws Exception;

}
