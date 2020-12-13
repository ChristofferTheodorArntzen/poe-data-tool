package com.carnnjoh.poedatatool.db.utils;

@FunctionalInterface
public interface UpdateAction {

	Result run() throws Exception;

}
