package com.example.servicebankingoperations.filter;

import com.example.servicebankingoperations.model.Client;
import com.example.servicebankingoperations.model.Email;
import com.example.servicebankingoperations.model.Phone;
import jakarta.persistence.criteria.Join;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class ClientSpecification {
    public static Specification<Client> dateOfBirthGreaterThan(Date birthDay) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThan(root.get("birthDay"), birthDay);
    }

    public static Specification<Client> phoneEquals(String phone) {
        return (root, query, criteriaBuilder) -> {
            Join<Client, Phone> phoneJoin = root.join("phone");
            return criteriaBuilder.equal(phoneJoin.get("phone"), phone);
        };
    }

    public static Specification<Client> fullNameLike(String fullName) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("fullName"), fullName + "%");
    }

    public static Specification<Client> emailEquals(String email) {
        return (root, query, criteriaBuilder) -> {
            Join<Client, Email> emailJoin = root.join("email");
            return criteriaBuilder.equal(emailJoin.get("email"), email);
        };
    }
}
