package com.gzie.dpi.Services;

import com.gzie.dpi.DTOs.CustomerDTO;
import com.gzie.dpi.Entities.CustomerEntity;
import com.gzie.dpi.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerEntity createCustomerIfNotExist(CustomerDTO customerData){
        Optional<CustomerEntity> existingCustomer= findCustomerByEmail(customerData.getEmail());
        if(existingCustomer.isPresent()){
            return existingCustomer.get();
        }

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setEmail(customerData.getEmail());
        customerEntity.setFirstName(customerData.getFirstName());
        customerEntity.setLastName(customerData.getLastName());
        customerRepository.save(customerEntity);
        return customerEntity;
    }

    public Optional<CustomerEntity> findCustomerByEmail(String email){
        return customerRepository.findByEmail(email);
    }
}
