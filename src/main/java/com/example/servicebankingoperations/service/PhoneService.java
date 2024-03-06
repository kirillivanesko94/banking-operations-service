package com.example.servicebankingoperations.service;

import com.example.servicebankingoperations.model.Client;
import com.example.servicebankingoperations.model.Phone;
import com.example.servicebankingoperations.repositories.ClientRepository;
import com.example.servicebankingoperations.repositories.PhoneRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PhoneService {
    private final ClientRepository clientRepository;
    private final PhoneRepository phoneRepository;

    public PhoneService(ClientRepository clientRepository, PhoneRepository phoneRepository) {
        this.clientRepository = clientRepository;
        this.phoneRepository = phoneRepository;
    }

    public void updatePhoneByClientUUID(UUID clientId, UUID phoneId, String phone) {
        Client client = clientRepository.findClientByUUID(clientId);
        List<Phone> phones = client.getPhones().stream()
                .peek(e -> {
                    if (e.getId().equals(phoneId)) {
                        e.setPhone(phone);
                    }
                })
                .collect(Collectors.toList());

        client.setPhones(phones);
        clientRepository.save(client);
    }

    public void deletePhoneByUUID(UUID clientId, UUID phoneId) {
        Client client = clientRepository.findClientByUUID(clientId);
        List<Phone> phones = client.getPhones();
        for (Phone phone : phones) {
            if (phone.getId().equals(phoneId)) {
                phones.remove(phone);
                client.setPhones(phones);
                clientRepository.save(client);
                phoneRepository.delete(phone);
                return;
            }
        }
    }
}
