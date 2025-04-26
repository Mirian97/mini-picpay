package com.quispe.mini_picpay.notification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quispe.mini_picpay.transaction.Transaction;

@Service
public class NotificationService {

	@Autowired
	NotificationConsumer notificationConsumer;

	@Autowired
	NotificationProducer notificationProducer;

	public void notify(Transaction transaction) {
		notificationProducer.sendNotification(transaction);
	}
}
