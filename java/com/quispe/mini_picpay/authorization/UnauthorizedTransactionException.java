package com.quispe.mini_picpay.authorization;

public class UnauthorizedTransactionException extends RuntimeException {

	public UnauthorizedTransactionException(String message) {
		super(message);
	}
}
