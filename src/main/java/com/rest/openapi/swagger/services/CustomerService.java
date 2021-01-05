package com.rest.openapi.swagger.services;



import com.rest.openapi.swagger.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();
    Customer findCustomerByPersonalId(String personalId);
    void save(Customer customer) throws Exception;
    void delete(String personalId) throws Exception;
    void update(Customer customer, String personalId) throws Exception;

}
