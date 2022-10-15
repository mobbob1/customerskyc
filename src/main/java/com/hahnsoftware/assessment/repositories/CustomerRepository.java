/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hahnsoftware.assessment.repositories;


import com.hahnsoftware.assessment.entities.Customer;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
/**
 * @author MOB
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
  @Override
  List<Customer> findAll();
  Customer findCustomerByContactNumber(String contactNumber);
  Customer findCustomerById(Long Id);
    
}
