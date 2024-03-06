package com.example.servicebankingoperations.controller;

import com.example.servicebankingoperations.service.EmailService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("email")
public class EmailController {
    private final EmailService service;

    public EmailController(EmailService service) {
        this.service = service;
    }

    @PatchMapping("update")
    public void updateEmail(@RequestParam UUID clientId,
                            @RequestParam UUID emailId,
                            @RequestParam String email) {
        service.updateEmailByClientUUID(clientId, emailId, email);
    }

    @DeleteMapping("delete")
    public void deleteEmail(@RequestParam UUID clientId,
                            @RequestParam UUID emailId) {
        service.deleteEmailByUUID(clientId, emailId);
    }
}
