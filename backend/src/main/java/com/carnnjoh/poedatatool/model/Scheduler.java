package com.carnnjoh.poedatatool.model;

import java.time.LocalDateTime;

public class Scheduler {

	private String id;
	private boolean isDisabled;
	private LocalDateTime lastActive;
	private String className;

	public Scheduler(boolean isDisabled, LocalDateTime lastActive, String className) {
		this.isDisabled = isDisabled;
		this.lastActive = lastActive;
		this.className = className;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isDisabled() {
		return isDisabled;
	}

	public void setDisabled(boolean disabled) {
		isDisabled = disabled;
	}

	public LocalDateTime getLastActive() {
		return lastActive;
	}

	public void setLastActive(LocalDateTime lastActive) {
		this.lastActive = lastActive;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
}
