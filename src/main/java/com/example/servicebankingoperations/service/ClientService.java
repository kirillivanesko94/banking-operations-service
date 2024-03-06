package com.example.servicebankingoperations.service;

import com.example.servicebankingoperations.filter.ClientSpecification;
import com.example.servicebankingoperations.model.Client;
import com.example.servicebankingoperations.model.ClientDto;
import com.example.servicebankingoperations.model.Email;
import com.example.servicebankingoperations.model.Phone;
import com.example.servicebankingoperations.repositories.ClientRepository;
import com.example.servicebankingoperations.repositories.EmailRepository;
import com.example.servicebankingoperations.repositories.PhoneRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

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
                clientDto.getLogin(),
                clientDto.getFullName(),
                clientDto.getBirthDay(),
                clientDto.getPassword(),
                clientDto.getAccountNumber(),
                clientDto.getStartDeposit(),
                clientDto.getBalance()
        );


        Email email = new Email(
                clientDto.getEmail()
        );

        newClient.addEmail(email);

        Phone phone = new Phone(
                clientDto.getPhone()
        );

        newClient.addPhone(phone);

        clientRepository.save(newClient);
//        emailRepository.save(email);
//        phoneRepository.save(phone);
    }

    public Page<Client> searchClients(Date birthDay, String phone, String fullName, String email, int pageNumber, int pageSize, String sortBy) {
        Specification<Client> spec = Specification.where(null);
        if (birthDay != null) {
            spec = spec.and(ClientSpecification.dateOfBirthGreaterThan(birthDay));
        }
        if (phone != null) {
            spec = spec.and(ClientSpecification.phoneEquals(phone));
        }
        if (fullName != null) {
            spec = spec.and(ClientSpecification.fullNameLike(fullName));
        }
        if (email != null) {
            spec = spec.and(ClientSpecification.emailEquals(email));
        }
        Sort sort = Sort.by(Sort.Direction.ASC, sortBy);
        PageRequest pageable = PageRequest.of(pageNumber, pageSize, sort);
        return clientRepository.findAll(spec, pageable);
    }
}
