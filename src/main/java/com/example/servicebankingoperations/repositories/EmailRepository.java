package com.example.servicebankingoperations.repositories;

import com.example.servicebankingoperations.model.Email;
import org.springframework.data.repository.CrudRepository;

public interface EmailRepository extends CrudRepository<Email, String> {
}
