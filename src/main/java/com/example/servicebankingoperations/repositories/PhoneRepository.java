package com.example.servicebankingoperations.repositories;

import com.example.servicebankingoperations.model.Phone;
import org.springframework.data.repository.CrudRepository;

public interface PhoneRepository extends CrudRepository<Phone, String> {
}
