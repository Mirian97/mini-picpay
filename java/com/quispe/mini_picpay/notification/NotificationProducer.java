package com.quispe.mini_picpay.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.quispe.mini_picpay.transaction.Transaction;

@Service
public class NotificationProducer {
	@Autowired
	KafkaTemplate<String, Transaction> kafkaTemplate;

	public void sendNotification(Transaction transaction) {
		kafkaTemplate.send("transaction-notification", transaction);
	}
}
