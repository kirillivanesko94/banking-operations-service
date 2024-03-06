package com.example.servicebankingoperations.repositories;

import com.example.servicebankingoperations.model.Client;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface ClientRepository extends CrudRepository<Client, String>, JpaSpecificationExecutor<Client> {
    @Query("SELECT c FROM Client c WHERE c.id = :id")
    Client findClientByUUID(@Param("id") UUID id);
}
