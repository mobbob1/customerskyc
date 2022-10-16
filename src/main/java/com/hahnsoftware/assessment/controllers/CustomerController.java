/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hahnsoftware.assessment.controllers;

import com.hahnsoftware.assessment.entities.Customer;
import com.hahnsoftware.assessment.exceptions.ResourceNotFoundException;
import com.hahnsoftware.assessment.repositories.CustomerRepository;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author MOB
 */
@RestController
@RequestMapping(value = "/api")
public class CustomerController {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/customers")
    public List<Customer> getListCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerId(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Customer customer = customerRepository.findCustomerById(id);
        if (null == customer) {
            new ResourceNotFoundException("Customer not found for this id :: " + id);
        }
        return ResponseEntity.ok().body(customer);
    }

    @PostMapping("/customers")
    public Customer addCustomer(@Valid @RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

   @PutMapping("/customers/{id}")
     public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") Long id,
      @Valid @RequestBody Customer customerDetails) throws ResourceNotFoundException {
         Customer customer = customerRepository.findCustomerById(id);
           if (null == customer) {
            new ResourceNotFoundException("Customer not found for this id :: " + id);
        }
         customer.setId(customerDetails.getId());
         customer.setContactNumber(customerDetails.getContactNumber());
         customer.setFirstName(customerDetails.getFirstName());
         customer.setSurname(customerDetails.getSurname());
         customer.setResidentialAddress(customerDetails.getResidentialAddress());
         customer.setEmailAddress(customerDetails.getEmailAddress());
         final Customer updatedCustomer = customerRepository.save(customer);
         return ResponseEntity.ok(updatedCustomer);
     }
 
     @DeleteMapping("/customers/{id}")
     public Map<String, Boolean> deleteCustomer(@PathVariable(value = "id") Long id)
       throws ResourceNotFoundException {
         Customer customer = customerRepository.findCustomerById(id);
          if (null == customer) {
            new ResourceNotFoundException("Customer not found for this id :: " + id);
        }
         customerRepository.delete(customer);
         Map<String, Boolean> response = new HashMap<>();
         response.put("deleted", Boolean.TRUE);
         return response;
     }
}
