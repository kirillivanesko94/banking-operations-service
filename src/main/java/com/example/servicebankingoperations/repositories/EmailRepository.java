package com.example.servicebankingoperations.repositories;

import com.example.servicebankingoperations.model.entity.Email;
import org.springframework.data.repository.CrudRepository;

public interface EmailRepository extends CrudRepository<Email, String> {
}
