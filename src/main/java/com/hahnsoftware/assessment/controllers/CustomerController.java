/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hahnsoftware.assessment.controllers;

import com.hahnsoftware.assessment.entities.Customer;
import com.hahnsoftware.assessment.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
/**
 *
 * @author MOB
 */
@Controller
@RequestMapping(value = "/customers")
public class CustomerController {
  private final CustomerRepository customerRepository;
  @Autowired
  public CustomerController(CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }
  @RequestMapping(
      method = RequestMethod.GET,
      produces = {"application/json"})
  public @ResponseBody List<Customer> listCustomers() {
    return customerRepository.findAll();
  }
  @RequestMapping(
      method = RequestMethod.POST,
      consumes = {"application/json"})
  public @ResponseBody void addCustomer(@RequestBody Customer customer) {
    customerRepository.save(customer);
  }
  @RequestMapping(
      method = RequestMethod.PUT,
      consumes = {"application/json"})
  public @ResponseBody void updateCustomer(@RequestBody Customer customer) {
    customerRepository.save(customer);
  }
  @RequestMapping(method = RequestMethod.DELETE)
  public @ResponseBody void deleteCustomer(@RequestParam("id") Long id) {
    customerRepository.deleteById(id);
  }
}
