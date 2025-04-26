package com.quispe.mini_picpay.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class AppConfig {

	@Bean
	NewTopic notificationTopic() {
		return TopicBuilder
				.name("transaction-notification")
				.build();
	}
}
