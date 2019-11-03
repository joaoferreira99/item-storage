package com.training.springboot.itemstorage.utils;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ItemStorageProperties {

	@Value("${notification.email.send:false}")
	private boolean sendNotificationEmail;

	@Value("${notification.email.uri}")
	private String sendNotificationEmailUri;

	@Value("notification.email.recipient")
	private String sendNotificationEmailRecipient;

	@Value("notification.email.subject.restock")
	private String sendNotificationEmailSubject;

	@Value("notification.email.subject.message")
	private String sendNotificationEmailMessage;

}
