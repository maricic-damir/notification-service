package com.levi9.code9.notification.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.levi9.code9.notification.email.EmailService;
import com.levi9.code9.notification.email.NotificationService;
import com.levi9.code9.notification.messaging.user.ProductsUserMessage;
import com.levi9.code9.notification.messaging.user.UserUpdateMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
@Slf4j
public class MessagesListener {

    private final NotificationService notificationService;

    @RabbitListener(queues = "user-update-queue")
    public void receiveUserMessage(String message) {
        UserUpdateMessage userUpdateMessage = null;
        try {
            userUpdateMessage = new ObjectMapper().readValue(message, UserUpdateMessage.class);
        } catch (JsonProcessingException e) {
            log.error("Error during reading message: %s", e);
            e.printStackTrace();
            return;
        }
        log.info("Message successfully received");
        if ("EMAIL".equals(userUpdateMessage.getNotificationType())) {
            notificationService.sendUserUpdate(userUpdateMessage.getEmail(), userUpdateMessage.getTypeOfOperation());
        }
    }

    @RabbitListener(queues = "product-info-queue")
    public void receiveProductMessage(String message) {
        ProductsUserMessage productsUserMessage = null;
        try {
            productsUserMessage = new ObjectMapper().readValue(message, ProductsUserMessage.class);
        } catch (JsonProcessingException e) {
            log.error("Error during reading message: %s", e);
            e.printStackTrace();
            return;
        }
        log.info("Message successfully received");
        if ("EMAIL".equals(productsUserMessage.getNotificationType())) {
            notificationService.sendReportMailForUser("Weekly", productsUserMessage.getEmail(), productsUserMessage.getProducts());
        }
    }
}
