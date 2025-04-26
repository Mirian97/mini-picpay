package com.quispe.mini_picpay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

@EnableJdbcAuditing
@SpringBootApplication
public class MiniPicpayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniPicpayApplication.class, args);
	}

}
