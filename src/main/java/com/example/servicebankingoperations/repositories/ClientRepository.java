package com.example.servicebankingoperations.repositories;

import com.example.servicebankingoperations.model.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface ClientRepository extends CrudRepository<Client, String> {
    @Query("SELECT u FROM Client u WHERE u.birthDay > :date")
    List<Client> findUsersByDateOfBirthAfter(@Param("date") Date date);

    @Query("SELECT c FROM Client c JOIN Phone p ON c.id = p.id WHERE p.phone = :phone")
    List<Client> findUsersByPhoneNumber(@Param("phone") String phone);

    @Query("SELECT u FROM Client u WHERE u.fullName LIKE :name")
    List<Client> findUsersByFullNameStartingWith(@Param("name") String name);

    @Query("SELECT c FROM Client c JOIN Email e ON c.id = e.id WHERE e.email = :email")
    List<Client> findUsersByEmail(@Param("email") String email);
}
