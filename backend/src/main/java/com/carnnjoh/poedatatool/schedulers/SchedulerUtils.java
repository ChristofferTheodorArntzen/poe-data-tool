package com.carnnjoh.poedatatool.schedulers;

public class SchedulerUtils {

	public static void execute(ScheduledTask block) {
		try {
			block.execute();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
