/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hahnsoftware.assessment.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * @author MOB
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "First Name is required")
    @Size(min = 2, message = "First Name should have atleast 2 characters")
    private String firstName;

    @Size(min = 2, message = "Surname should have atleast 2 characters")
    @NotBlank(message = "Surname is required")
    private String surname;

    @NotBlank(message = "Residential Address is required")
    private String residentialAddress;

    @Size(min = 10, message = "Contact Numnber should have at least 10 characters")
    @NotBlank(message = "Contact Number is required")
   @Column(unique=true)
    private String contactNumber;

    @Email
    @NotBlank
    @Column(unique=true)
    private String emailAddress;

}
