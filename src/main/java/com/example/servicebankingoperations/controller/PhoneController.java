package com.example.servicebankingoperations.controller;

import com.example.servicebankingoperations.service.PhoneService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("phone")
public class PhoneController {
    private final PhoneService service;

    public PhoneController(PhoneService service) {
        this.service = service;
    }

    @PatchMapping("update")
    public void updatePhoneNumber(@RequestParam UUID clientId,
                                  @RequestParam UUID phoneId,
                                  @RequestParam String phone) {
        service.updatePhoneByClientUUID(clientId, phoneId, phone);
    }
    @DeleteMapping("delete")
    public void deletePhoneNumber(@RequestParam UUID clientId,
                                  @RequestParam UUID phoneId) {
        service.deletePhoneByUUID(clientId, phoneId);
    }
}
