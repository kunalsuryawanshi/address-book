package com.bridgelabz.addressbook.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Purpose: To Get The Basic Structure of Data
 *
 * @author : Kunal Suryawanshi
 * @since : 13-12-2021
 */
@Entity
@Data
@Table(name = "address_book")
public class AddressBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String address;

    private String city;

    private String state;

    private String zip;

    @Column(unique = true)
    private String phoneNumber;

    private String email;

    @CreationTimestamp
    private LocalDateTime createdOn;

    @UpdateTimestamp
    private LocalDateTime UpdatedOn;
}
