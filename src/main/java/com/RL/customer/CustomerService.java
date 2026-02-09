package com.RL.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public CustomerEntity SaveCustomer(CustomerEntity customer) {
        return customerRepository.save(customer);
    }
}
