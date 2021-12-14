package com.bridgelabz.addressbook.controller;

import com.bridgelabz.addressbook.dto.AddressBookDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/addressBookApp")
public class AddressBookController {

    @RequestMapping("/book")
    public ResponseEntity<String> getAllContact() {
        return new ResponseEntity<String>("Get Call Success", HttpStatus.OK);
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<String> getContactById(@PathVariable("id") int id) {
        return new ResponseEntity<String>("Get Call Success for id : " + id, HttpStatus.OK);
    }

    @PostMapping("/book")
    public ResponseEntity<String> addContact(@RequestBody AddressBookDto addressBookDto) {
        return new ResponseEntity<>("Created Contact for : " + addressBookDto, HttpStatus.OK);
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<String> updateContact(@PathVariable(value = "id") int id, @RequestBody AddressBookDto addressBookDto) {
        return new ResponseEntity<>("Updated Contact for : " + addressBookDto, HttpStatus.OK);
    }

    @DeleteMapping("/book/{id}")
    public ResponseEntity<String> deleteContact(@PathVariable(value = "id") int id) {
        return new ResponseEntity<>("Delete Call Success for id: " + id, HttpStatus.OK);
    }
}

