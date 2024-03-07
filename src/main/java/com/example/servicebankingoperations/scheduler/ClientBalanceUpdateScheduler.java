package com.example.servicebankingoperations.scheduler;

import com.example.servicebankingoperations.model.Client;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ClientBalanceUpdateScheduler {
    private final Logger logger = LoggerFactory.getLogger(ClientBalanceUpdateScheduler.class);
    @PersistenceContext
    private EntityManager entityManager;

    @Scheduled(cron = "0 * * * * *")
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void updateClientBalances() {
        logger.info("Method: updateClientBalances it was launched");
        List<Client> clients = entityManager.createQuery("SELECT c FROM Client c", Client.class).getResultList();
        clients.forEach(client -> {
            BigDecimal newBalance = client.getBalance().multiply(new BigDecimal("1.05")).setScale(2, RoundingMode.HALF_UP);
            BigDecimal maxBalance = client.getStartDeposit().multiply(new BigDecimal("2.07")).setScale(2, RoundingMode.HALF_UP);
            if (newBalance.compareTo(maxBalance) <= 0) {
                client.setBalance(newBalance);
            } else {
                client.setBalance(maxBalance);
            }
            entityManager.merge(client);

        });
    }
}
