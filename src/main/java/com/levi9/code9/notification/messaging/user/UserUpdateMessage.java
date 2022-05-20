package com.levi9.code9.notification.messaging.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.levi9.code9.notification.messaging.BaseMessage;
import lombok.*;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Getter
public class UserUpdateMessage extends BaseMessage {

    @JsonProperty("email")
    String email;

    @JsonProperty("type_of_operation")
    String typeOfOperation;

    @JsonProperty("notification_type")
    String notificationType;

}
