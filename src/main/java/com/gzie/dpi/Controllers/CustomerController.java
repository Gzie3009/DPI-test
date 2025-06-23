package com.gzie.dpi.Controllers;

import com.gzie.dpi.DTOs.CustomerDTO;
import com.gzie.dpi.Entities.CustomerEntity;
import com.gzie.dpi.Services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDTO> saveCustomer(@Valid  @RequestBody CustomerDTO customer) {
        CustomerEntity customerEntity = customerService.createCustomerIfNotExist(customer);
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setId(customerEntity.getId());
        customerDTO.setEmail(customerEntity.getEmail());
        customerDTO.setFirstName(customerEntity.getFirstName());
        customerDTO.setLastName(customerEntity.getLastName());
        return new ResponseEntity<>(customerDTO, HttpStatus.CREATED);
    }
}
