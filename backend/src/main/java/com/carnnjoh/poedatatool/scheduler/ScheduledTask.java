package com.carnnjoh.poedatatool.scheduler;

import com.carnnjoh.poedatatool.model.PrivateStashTabRequest;

import java.util.List;

public interface ScheduledTask {


	void execute();

	PrivateStashTabRequest fetchRequest(Integer tabIndex);

}
