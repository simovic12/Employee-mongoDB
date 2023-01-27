package com.example.employee.mongoDB.repository;

import com.example.employee.mongoDB.entity.Contact;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContactRepository extends MongoRepository<Contact, Long> {
    Optional<Contact> findContactByEmployeeId(Long id);
}
