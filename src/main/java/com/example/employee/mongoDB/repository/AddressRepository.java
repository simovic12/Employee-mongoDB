package com.example.employee.mongoDB.repository;

import com.example.employee.mongoDB.entity.Address;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends MongoRepository<Address, Long> {
    Optional<Address> findByEmployeeId(Long id);
}
