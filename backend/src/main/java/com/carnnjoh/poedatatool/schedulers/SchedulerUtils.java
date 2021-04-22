package com.carnnjoh.poedatatool.schedulers;

public class SchedulerUtils {

	// Global sleep variable to run after most http request to the poe API to avoid rate limiting
	public static final int HTTP_REQUEST_SLEEP = 20000;

	public static void execute(ScheduledTask block) {
		try {
			block.execute();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
