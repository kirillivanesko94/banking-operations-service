package com.example.servicebankingoperations.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "phone")
public class Phone {
    @Id
    @Column(name = "client_id")
    private UUID clientId;
    @Column(name = "phone")
    private String phone;

    public Phone() {
    }

    public Phone(UUID clientId, String phone) {
        this.clientId = clientId;
        this.phone = phone;

    }

    public UUID getClientId() {
        return clientId;
    }

    public void setClientId(UUID clientId) {
        this.clientId = clientId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
