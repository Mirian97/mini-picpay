package com.quispe.mini_picpay.notification;

public record Notification(String status) {

	public boolean wasNotified() {
		return status().equalsIgnoreCase("success");
	}
}
