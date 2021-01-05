package com.rest.openapi.swagger.services;
import com.rest.openapi.swagger.entity.Customer;
import com.rest.openapi.swagger.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService{

    private CustomerRepository repository;

    private static final String CUSTOMER_ALREADY_EXISTS = "Customer already exists";
    private static final String CUSTOMER_DOES_NOT_EXIST = "Customer does not exist";

    @Autowired
    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    public CustomerServiceImpl(){}

    @Override
    public List<Customer> findAll(){
        return repository.findAll();
    }

    @Override
    public Customer findCustomerByPersonalId(String personalId){
        Optional<Customer> optionalCustomer = this.repository.findDistinctByPersonalId(personalId);
        return optionalCustomer.orElse(null);
    }

    @Override
    public void save(Customer customer) throws Exception {
        Optional<Customer> optionalCustomer  = this.repository.findDistinctByPersonalId(customer.getPersonalId());

        if(optionalCustomer.isPresent()){
            throw new Exception(CUSTOMER_ALREADY_EXISTS);
        }
        this.repository.save(customer);
    }

    @Override
    public void delete(String personalId) throws Exception {
        Optional<Customer> optionalCustomer  = this.repository.findDistinctByPersonalId(personalId);

        if(optionalCustomer.isPresent()){
            this.repository.deleteByPersonalId(personalId);
        }else{
            throw new Exception(CUSTOMER_DOES_NOT_EXIST);
        }
    }

    @Override
    public void update(Customer newCustomer, String personalId) {
        Optional<Customer> optionalCustomer  = this.repository.findDistinctByPersonalId(personalId);
        if(optionalCustomer.isPresent()){
            optionalCustomer.get().setName(newCustomer.getName());
            optionalCustomer.get().setAge(newCustomer.getAge());
        }

        this.repository.save(newCustomer);
    }

}
