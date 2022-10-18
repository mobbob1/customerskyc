/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hahnsoftware.assessment.controllers;

import com.hahnsoftware.assessment.entities.Customer;
import com.hahnsoftware.assessment.exceptions.ResourceNotFoundException;
import com.hahnsoftware.assessment.repositories.CustomerRepository;
import com.hahnsoftware.assessment.response.ResponseHandler;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
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
      @ResponseStatus(HttpStatus.OK)
    public List<Customer> getListCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/customers/{id}")
      @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Customer> getCustomerId(@PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Customer customer = customerRepository.findCustomerById(id);
        if (null == customer) {
            new ResourceNotFoundException("Customer not found for this id :: " + id);
        }
        return ResponseEntity.ok().body(customer);
    }

    @PostMapping("/customers")
    public ResponseEntity<Object> addCustomer(@Valid @RequestBody Customer customer) { 
            try {
         customerRepository.save(customer);
            return ResponseHandler.generateResponse("Successfully added new customer", HttpStatus.OK, customer);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

   @PutMapping("/customers/{id}")
     @ResponseStatus(HttpStatus.OK)
     public ResponseEntity<Customer> updateCustomer(@PathVariable(value = "id") Long id,
      @Valid @RequestBody Customer customerDetails) throws ResourceNotFoundException {
         Customer customer = customerRepository.findCustomerById(id);
           if (null != customer) {
          
      
         customer.setContactNumber(customerDetails.getContactNumber());
         customer.setFirstName(customerDetails.getFirstName());
         customer.setSurname(customerDetails.getSurname());
         customer.setResidentialAddress(customerDetails.getResidentialAddress());
         customer.setEmailAddress(customerDetails.getEmailAddress());
         final Customer updatedCustomer = customerRepository.save(customer);
         return ResponseEntity.ok(updatedCustomer);
           }else{
                 new ResourceNotFoundException("Customer not found for this id :: " + id);
        }
           return null;
     }
 
     @DeleteMapping("/customers/{id}")
       @ResponseStatus(HttpStatus.OK)
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
