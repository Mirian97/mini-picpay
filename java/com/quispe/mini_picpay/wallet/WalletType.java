package com.quispe.mini_picpay.wallet;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WalletType {
	COMMOM(1), SHOPKEEPER(2);

	private Integer value;
}
