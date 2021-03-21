package com.carnnjoh.poedatatool.schedulers;

@FunctionalInterface
public interface ScheduledTask {

	void execute() throws Exception;

}
