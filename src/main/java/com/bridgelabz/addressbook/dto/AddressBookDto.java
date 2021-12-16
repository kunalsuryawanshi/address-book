package com.bridgelabz.addressbook.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Purpose: To Get Data From End User
 *
 * @author : Kunal Suryawanshi
 * @since : 13-12-2021
 */
@Data
public class AddressBookDto {
    @NotNull(message = "Name Should Not be Empty")
    @Pattern(regexp = "^[A-Z]{1}[a-z]{2,}+[\\s]?[A-Z]{1}[a-z]{2,}$",
            message = "Name First Letter Should be Capital & Minimum 3 Characters.")
    private String name;

    @NotNull(message = "Address Should Not be Empty")
    private String address;

    @NotNull(message = "City Should Not be Empty")
    @Pattern(regexp = "^[A-Z a-z]+$", message = "City Name Contains Only Alphabets.")
    private String city;

    @NotNull(message = "State Should Not be Empty")
    @Pattern(regexp = "^[A-Z a-z]+$", message = "State Name Contains Only Alphabets.")
    private String state;

    @NotNull(message = "Zip Should Not be Empty")
    @Pattern(regexp = "\\d{6}", message = "Zip Code Contains Only Six Digits.")
    private String zip;

    @NotNull(message = "Phone Number Should Not be Empty")
    @Pattern(regexp = "^\\+\\d{2}[ ]\\d{10}$", message = "Phone Number With Country Code & Phone Number Contains 10 Digits Only.")
    private String phoneNumber;

    @NotNull(message = "Email Should Not be Empty")
    @Pattern(regexp = "([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})", message = "Invalid Email")
    private String email;
}
