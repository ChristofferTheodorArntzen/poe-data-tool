package com.carnnjoh.poedatatool.schedulers;

public class SchedulerUtils {

	// Global sleep variable to run after most http request to the poe API to avoid rate limiting
	public static final int HTTP_REQUEST_SLEEP = 2000;

	public static void execute(ScheduledTask block) {
		try {
			block.execute();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static void trySleep() {
		trySleep(HTTP_REQUEST_SLEEP);
	}

	public static void trySleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
