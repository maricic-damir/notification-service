package com.levi9.code9.notification.email;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NotificationService {

    private final EmailService emailService;

    public void sendReportMailForUser(String type, String email, List<String> receipts) {
        String message = emailService.generateReportText(receipts);
        emailService.sendMailReport(type, email, message);
    }

    public void sendUserUpdate(String email, String type) {
        String message = emailService.generateUserUpdateText(email, type);
        emailService.sendMailUserUpdate(email, message);
    }
}
