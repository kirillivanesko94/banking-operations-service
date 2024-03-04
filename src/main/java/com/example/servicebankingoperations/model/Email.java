package com.example.servicebankingoperations.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "email")
public class Email {
    @Id
    @Column(name = "id")
    private UUID id;
    @Column(name = "client_id")
    private UUID clientId;
    @Column(name = "email")
    private String email;

    public Email() {
    }

    public Email(UUID id, UUID clientId, String email) {
        this.id = id;
        this.clientId = clientId;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
