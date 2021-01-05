package com.rest.openapi.swagger.repository;

import com.rest.openapi.swagger.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
    Optional<Customer> findDistinctByPersonalId(String personalId);
    void deleteByPersonalId(String personalId);
}
