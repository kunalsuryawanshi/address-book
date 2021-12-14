package com.bridgelabz.addressbook.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;

/**
 * Purpose: to configure model mapper
 *
 * @author: Kunal Suryawanshi
 * @since: 13-12-2021
 */
@Configuration
public class AddressBookConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
