package com.example.servicebankingoperations.controller;

import com.example.servicebankingoperations.service.TransferBetweenFundsService;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("transfer")
public class TransferController {
    private final TransferBetweenFundsService service;

    public TransferController(TransferBetweenFundsService service) {
        this.service = service;
    }

    @PatchMapping
    public void transferMoney(@RequestParam UUID senderClientUUID,
                              @RequestParam UUID recepientClientUUID,
                              @RequestParam BigDecimal transferSum) {
        service.transferMoney(senderClientUUID, recepientClientUUID, transferSum);
    }
}
