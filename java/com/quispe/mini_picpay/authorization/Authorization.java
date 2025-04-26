package com.quispe.mini_picpay.authorization;

public record Authorization(String status, Data data) {
	public record Data(boolean authorization) {
	}

	public boolean isAuthorized() {
		return data.authorization();
	}
}
