package com.example.servicebankingoperations.service;

import com.example.servicebankingoperations.model.entity.Client;
import com.example.servicebankingoperations.model.security.User;
import com.example.servicebankingoperations.repositories.ClientRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientAuthenticationService implements UserDetailsService {
    private final ClientRepository repository;

    public ClientAuthenticationService(ClientRepository repository) {
        this.repository = repository;
    }

    private Optional<Client> findClientByFullName(String name) {
        return repository.findClientByFullName(name);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = findClientByFullName(username).orElseThrow(() -> new UsernameNotFoundException(String.format("Client with name %s not found", username)));
        return clientToSecurityUser(client);
    }

    private User clientToSecurityUser(Client client) {
        return new User(
                client.getId(),
                client.getLogin(),
                client.getPassword()
        );
    }
}
