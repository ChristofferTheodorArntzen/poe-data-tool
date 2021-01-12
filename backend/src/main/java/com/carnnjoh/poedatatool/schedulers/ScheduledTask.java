package com.carnnjoh.poedatatool.schedulers;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface ScheduledTask {


	void execute() throws JsonProcessingException;

}
