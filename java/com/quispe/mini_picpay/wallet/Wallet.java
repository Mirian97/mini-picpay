package com.quispe.mini_picpay.wallet;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table(name = "WALLETS")
public record Wallet(
		@Id Long id,
		String fullName,
		String email,
		String password,
		String cpf,
		Integer type,
		BigDecimal balance) {

	public Wallet debit(BigDecimal value) {
		return new Wallet(id, fullName, email, password, cpf, type, balance.subtract(value));
	}

	public Wallet credit(BigDecimal value) {
		return new Wallet(id, fullName, email, password, cpf, type, balance.add(value));
	}
}
