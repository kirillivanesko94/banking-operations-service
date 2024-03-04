package com.example.servicebankingoperations.service;

import com.example.servicebankingoperations.model.Client;
import com.example.servicebankingoperations.model.ClientDto;
import com.example.servicebankingoperations.model.Email;
import com.example.servicebankingoperations.model.Phone;
import com.example.servicebankingoperations.repositories.ClientRepository;
import com.example.servicebankingoperations.repositories.EmailRepository;
import com.example.servicebankingoperations.repositories.PhoneRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final EmailRepository emailRepository;
    private final PhoneRepository phoneRepository;
//    private final BCryptPasswordEncoder passwordEncoder;

    public ClientService(ClientRepository repository, EmailRepository emailRepository, PhoneRepository phoneRepository) {
        this.clientRepository = repository;
        this.emailRepository = emailRepository;
        this.phoneRepository = phoneRepository;
    }

    @Transactional
    public void addClient(ClientDto clientDto) {
        Client newClient = new Client(
                clientDto.getId(),
                clientDto.getLogin(),
                clientDto.getFullName(),
                clientDto.getBirthDay(),
                clientDto.getPassword(),
                clientDto.getAccountNumber(),
                clientDto.getStartDeposit(),
                clientDto.getBalance()
        );

        Email email = new Email(
                UUID.randomUUID(),
                clientDto.getId(),
                clientDto.getEmail()
        );

        Phone phone = new Phone(
                clientDto.getId(),
                clientDto.getPhone()
        );

        clientRepository.save(newClient);
        emailRepository.save(email);
        phoneRepository.save(phone);
    }

    @Transactional
    public List<Client> searchClients(String fullName, Date birthDate, String email, String phone) {
        List<Client> clients = new ArrayList<>();

        if (fullName != null) {
            clients.addAll(clientRepository.findUsersByFullNameStartingWith(fullName));
        }
        if (birthDate != null) {
            clients.addAll(clientRepository.findUsersByDateOfBirthAfter(birthDate));
        }
        if (email != null) {
            clients.addAll(clientRepository.findUsersByEmail(email));
        }
        if (phone != null) {
            clients.addAll(clientRepository.findUsersByPhoneNumber(phone));
        }

        return clients;
    }
}
