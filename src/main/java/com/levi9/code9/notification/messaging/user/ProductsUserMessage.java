package com.levi9.code9.notification.messaging.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.levi9.code9.notification.messaging.BaseMessage;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Getter
public class ProductsUserMessage extends BaseMessage {

    @JsonProperty("email")
    String email;

    @JsonProperty("products")
    List<String> products;

    @JsonProperty("notification_type")
    String notificationType;

}
