package com.example.servicebankingoperations.service;

import com.example.servicebankingoperations.exception.DeletePhoneNumberException;
import com.example.servicebankingoperations.model.entity.Client;
import com.example.servicebankingoperations.model.entity.Phone;
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

    public void addPhone(UUID clientId, String phone) {
        Client client = clientRepository.findClientByUUID(clientId);

        Phone newPhone = new Phone();
        newPhone.setId(UUID.randomUUID());
        newPhone.setPhone(phone);
        newPhone.setClient(client);

        List<Phone> phones = client.getPhones();
        phones.add(newPhone);

        client.setPhones(phones);

        clientRepository.save(client);
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
            if (phone.getId().equals(phoneId) && phones.size() > 1) {
                phones.remove(phone);
                client.setPhones(phones);
                clientRepository.save(client);
                phoneRepository.delete(phone);
                return;
            } else {
                throw new DeletePhoneNumberException("The UUID is specified incorrectly and the user has only one phone number");
            }
        }
    }
}
