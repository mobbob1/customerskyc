/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hahnsoftware.assessment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hahnsoftware.assessment.controllers.CustomerController;
import com.hahnsoftware.assessment.entities.Customer;
import com.hahnsoftware.assessment.repositories.CustomerRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;




/**
 *
 * @author MOB
 */
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
 @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    
    @MockBean
    CustomerRepository customerRepository;
    
    Customer customer1 = new Customer(1l,"Michael","Osei","C2 Sakomono Ghana","0244881122","michaelosei@yahoo.com");
    Customer customer2 = new Customer(2l,"Emmanuel","Oboyie","BLK 65 C Accra","0234112211","emmanueloboyie2002@gmail.com");
    Customer customer3 = new Customer(3l,"George","Petra","BLK 54 OSU ACCRA","055312311","georgepentra@ymail.com");

    
    
    
    @Test
public void getAllCustomers() throws Exception {
    List<Customer> records = new ArrayList<>(Arrays.asList(customer1, customer2, customer3));
    
    Mockito.when(customerRepository.findAll()).thenReturn(records);
    
    mockMvc.perform(MockMvcRequestBuilders
            .get("/api/customers")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(3)))
            .andExpect(jsonPath("$[2].firstName", is("George")));
}




@Test
public void getCustomerById() throws Exception {
    Mockito.when(customerRepository.findById(customer1.getId())).thenReturn(java.util.Optional.of(customer1));

    mockMvc.perform(MockMvcRequestBuilders
            .get("/api/customers/6")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
           .andExpect(jsonPath("$", notNullValue()))
            .andExpect(jsonPath("$.firstName", is("Joseph")));
}


@Test
public void createRecord_success() throws Exception {
    Customer record = Customer.builder()
            .firstName("John")
            .surname("Patel")
            .residentialAddress("Sakomo Ghana")
            .emailAddress("johnpatel@yahoo.com")
            .build();

    Mockito.when(customerRepository.save(record)).thenReturn(record);

    MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/customers")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(record));

    mockMvc.perform(mockRequest)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", notNullValue()))
            .andExpect(jsonPath("$.firstName", is("John")));
    }


@Test
public void updateCustomerRecord() throws Exception {
    Customer updatedCustomer = Customer.builder()
            .id(1l)
            .firstName("Rabuos")
            .surname("Danso")
            .residentialAddress("Labone Ghana")
            .emailAddress("dansorabuos@gmail.com")
            .build();

    Mockito.when(customerRepository.findById(customer1.getId())).thenReturn(Optional.of(customer1));
    Mockito.when(customerRepository.save(updatedCustomer)).thenReturn(updatedCustomer);

    MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/patient")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content(this.mapper.writeValueAsString(updatedCustomer));

    mockMvc.perform(mockRequest)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", notNullValue()))
            .andExpect(jsonPath("$.name", is("Rayven Zambo")));
}

@Test
public void deleteCustomer() throws Exception {
    Mockito.when(customerRepository.findById(customer1.getId())).thenReturn(Optional.of(customer1));

     try {
         mockMvc.perform(MockMvcRequestBuilders
                 .delete("/id/customers/6")
                 .contentType(MediaType.APPLICATION_JSON))
                 .andExpect(status().isOk());
     } catch (Exception ex) {
         Logger.getLogger(CustomerControllerTest.class.getName()).log(Level.SEVERE, null, ex);
     }
}
}
