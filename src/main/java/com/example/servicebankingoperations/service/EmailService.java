package com.example.servicebankingoperations.service;

import com.example.servicebankingoperations.exception.DeleteEmailException;
import com.example.servicebankingoperations.model.entity.Client;
import com.example.servicebankingoperations.model.entity.Email;
import com.example.servicebankingoperations.repositories.ClientRepository;
import com.example.servicebankingoperations.repositories.EmailRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmailService {
    private final ClientRepository clientRepository;
    private final EmailRepository emailRepository;

    public EmailService(ClientRepository clientRepository, EmailRepository emailRepository) {
        this.clientRepository = clientRepository;
        this.emailRepository = emailRepository;
    }

    public void addEmail(UUID clientId, String email) {
        Client client = clientRepository.findClientByUUID(clientId);

        Email newEmail = new Email();
        newEmail.setId(UUID.randomUUID());
        newEmail.setEmail(email);
        newEmail.setClient(client);

        List<Email> emails = client.getEmails();
        emails.add(newEmail);

        client.setEmails(emails);

        clientRepository.save(client);
    }

    public void updateEmailByClientUUID(UUID clientId, UUID emailId, String email) {
        Client client = clientRepository.findClientByUUID(clientId);
        List<Email> emails = client.getEmails().stream()
                .peek(e -> {
                    if (e.getId().equals(emailId)) {
                        e.setEmail(email);
                    }
                })
                .collect(Collectors.toList());

        client.setEmails(emails);
        clientRepository.save(client);
    }

    public void deleteEmailByUUID(UUID clientId, UUID emailId) {
        Client client = clientRepository.findClientByUUID(clientId);
        List<Email> emails = client.getEmails();
        for (Email email : emails) {
            if (email.getId().equals(emailId) && emails.size() > 1) {
                emails.remove(email);
                client.setEmails(emails);
                clientRepository.save(client);
                emailRepository.delete(email);
                return;
            } else {
                throw new DeleteEmailException("the UUID is specified incorrectly and the user has only one email address");
            }
        }
    }
}
