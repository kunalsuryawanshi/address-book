package com.bridgelabz.addressbook;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Purpose: to stimulate Address Book Application
 *
 * @author : Kunal Suryawanshi
 * @since : 13-12-2021
 */
@SpringBootApplication
@EnableSwagger2
public class AddressBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(AddressBookApplication.class, args);
    }

}
