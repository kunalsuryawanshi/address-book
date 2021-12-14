package com.bridgelabz.addressbook.controller;

import com.bridgelabz.addressbook.dto.AddressBookDto;
import com.bridgelabz.addressbook.entity.AddressBook;
import com.bridgelabz.addressbook.service.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/addressBookApp")
public class AddressBookController {
    @Autowired
    AddressBookService addressBookService;

    @RequestMapping("/book")
    public List<AddressBookDto> getAllContact() {
        return addressBookService.getAllContact();
    }

    @GetMapping("/book/{id}")
    public AddressBookDto getContactById(@PathVariable("id") int id) {
        return addressBookService.getContactById(id);
    }

    @PostMapping("/book")
    public String addContact(@Valid @RequestBody AddressBookDto addressBookDto) {
        return addressBookService.addContact(addressBookDto);
    }

    @PutMapping("/book/{id}")
    public String updateContact(@PathVariable(value = "id") int id,@Valid @RequestBody AddressBookDto addressBookDto) {
        return addressBookService.updateContact(id, addressBookDto);
    }

    @DeleteMapping("/book/{id}")
    public String deleteContact(@PathVariable(value = "id") int id) {
        return addressBookService.deleteContactById(id);
    }
}

