package com.example.servicebankingoperations.service;

import com.example.servicebankingoperations.filter.ClientSpecification;
import com.example.servicebankingoperations.model.Client;
import com.example.servicebankingoperations.model.ClientDto;
import com.example.servicebankingoperations.model.Email;
import com.example.servicebankingoperations.model.Phone;
import com.example.servicebankingoperations.repositories.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    private final PasswordEncoder passwordEncoder;

    public ClientService(ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void addClient(ClientDto clientDto) {
        Client newClient = new Client(
                clientDto.getLogin(),
                clientDto.getFullName(),
                clientDto.getBirthDay(),
                passwordEncoder.encode(clientDto.getPassword()),
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
