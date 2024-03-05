package com.example.servicebankingoperations.repositories;

import com.example.servicebankingoperations.model.Client;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface ClientRepository extends CrudRepository<Client, String>, JpaSpecificationExecutor<Client> {

}
