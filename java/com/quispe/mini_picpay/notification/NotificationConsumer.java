package com.quispe.mini_picpay.notification;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.quispe.mini_picpay.transaction.Transaction;

@Service
public class NotificationConsumer {
	private final RestClient restClient;

	public NotificationConsumer(RestClient.Builder builder) {
		this.restClient = builder
				.baseUrl("https://util.devi.tools/api/v1/notify")
				.build();
	}

	@KafkaListener(topics = "transaction-notification", groupId = "mini-picpay")
	public void receiveNotification(Transaction transaction) {
		var response = restClient
				.get()
				.retrieve()
				.toEntity(Notification.class);

		if (response.getStatusCode().isError() || !response.getBody().wasNotified()) {
			throw new NotificationException("Error sending notification");
		}
	}
}
