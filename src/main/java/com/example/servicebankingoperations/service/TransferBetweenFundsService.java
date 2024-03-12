package com.example.servicebankingoperations.service;

import com.example.servicebankingoperations.exception.TransferMoneyException;
import com.example.servicebankingoperations.model.entity.Client;
import com.example.servicebankingoperations.repositories.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class TransferBetweenFundsService {
    private final ClientRepository repository;
    private final Logger logger = LoggerFactory.getLogger(TransferBetweenFundsService.class);


    public TransferBetweenFundsService(ClientRepository repository) {
        this.repository = repository;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void transferMoney(UUID senderClientUUID, UUID recepientClientUUID, BigDecimal transferSum) {
        Client senderClient = repository.findClientByUUID(senderClientUUID);
        Client recipientClient = repository.findClientByUUID(recepientClientUUID);

        if (isNegativeBalance(senderClient)) {
            throw new TransferMoneyException(transferSum);
        }

        senderClient.setBalance(senderClient.getBalance().subtract(transferSum));
        recipientClient.setBalance(recipientClient.getBalance().add(transferSum));
    }

    private boolean isNegativeBalance(Client senderClient) {
        return senderClient.getBalance().compareTo(BigDecimal.ZERO) < 0;
    }
}
