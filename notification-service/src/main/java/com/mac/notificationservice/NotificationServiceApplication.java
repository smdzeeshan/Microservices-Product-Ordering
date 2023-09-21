package com.mac.notificationservice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.CompletableFuture;

@SpringBootApplication
@Slf4j
@RequiredArgsConstructor
public class NotificationServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(NotificationServiceApplication.class, args);
	}

	@KafkaListener(topics = "notificationTopic")
	public void handleNotification(OrderPlacedEvent orderPlacedEvent){
		log.info("Received notification for Order: {}" , orderPlacedEvent.getOrderNumber());


		/*EmailDetails emailDetails = EmailDetails.builder()
										.subject("Order placed")
										.msgBody("Order has been placed with order id: " + orderPlacedEvent.getOrderNumber())
										.recipient("smdzeeshanhaider@gmail.com")
										.build();
		log.info("emailDetails: {}", emailDetails);
		String order_placed = emailService.sendSimpleMail(emailDetails);
		log.info("order_placed : {}" , order_placed);*/
	}


}
