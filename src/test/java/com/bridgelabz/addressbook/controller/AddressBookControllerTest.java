package com.bridgelabz.addressbook.controller;

import com.bridgelabz.addressbook.dto.AddressBookDto;
import com.bridgelabz.addressbook.service.AddressBookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddressBookControllerTest {

    @InjectMocks
    private AddressBookController addressBookController;
    @Mock
    private AddressBookService addressBookService;

    @Test
    void whenGetContactCalled_shouldReturnListOfContact() {
        List<AddressBookDto> addressBookDtoList = new ArrayList<>();

        AddressBookDto dtoContact1 = new AddressBookDto();
        dtoContact1.setName("Kunal Suryawanshi");
        dtoContact1.setAddress("304/A");
        dtoContact1.setCity("Thane");
        dtoContact1.setState("Maharashtra");
        dtoContact1.setZip("400606");
        dtoContact1.setPhoneNumber("+91 9594000000");
        dtoContact1.setEmail("Test1@gmail.com");
        addressBookDtoList.add(dtoContact1);

        AddressBookDto dtoContact2 = new AddressBookDto();
        dtoContact1.setName("Pratik Shinde");
        dtoContact1.setAddress("304/A");
        dtoContact1.setCity("Thane");
        dtoContact1.setState("Maharashtra");
        dtoContact1.setZip("400606");
        dtoContact1.setPhoneNumber("+91 9594000000");
        dtoContact1.setEmail("Test1@gmail.com");
        addressBookDtoList.add(dtoContact2);

        when(addressBookService.getAllContact()).thenReturn(addressBookDtoList);
        List<AddressBookDto> actualResponse = addressBookController.getAllContact();
        for (int i = 0; i < actualResponse.size(); i++) {
            assertEquals(addressBookDtoList.get(i).getName(), actualResponse.get(i).getName());
            assertEquals(addressBookDtoList.get(i).getAddress(), actualResponse.get(i).getAddress());
            assertEquals(addressBookDtoList.get(i).getCity(), actualResponse.get(i).getCity());
            assertEquals(addressBookDtoList.get(i).getState(), actualResponse.get(i).getState());
            assertEquals(addressBookDtoList.get(i).getZip(), actualResponse.get(i).getZip());
            assertEquals(addressBookDtoList.get(i).getPhoneNumber(), actualResponse.get(i).getPhoneNumber());
            assertEquals(addressBookDtoList.get(i).getEmail(), actualResponse.get(i).getEmail());
        }
    }

    @Test
    void whenAddContactCalled_shouldReturnSuccessMessage() {
        String expectedMessage = "Contact Added Successfully";

        AddressBookDto dtoContact = new AddressBookDto();
        dtoContact.setName("Kunal Suryawanshi");
        dtoContact.setAddress("304/A");
        dtoContact.setCity("Thane");
        dtoContact.setState("Maharashtra");
        dtoContact.setZip("400606");
        dtoContact.setPhoneNumber("+91 9594000000");
        dtoContact.setEmail("Test1@gmail.com");

        when(addressBookService.addContact(dtoContact)).thenReturn(expectedMessage);
        String actualMessage = addressBookController.addContact(dtoContact);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void whenUpdateContactCalled_shouldReturnSuccessMessage() {
        String expectedMessage = "Contact Updated Successfully";
        int id = 1;
        AddressBookDto dtoContact = new AddressBookDto();
        dtoContact.setName("Kunal Suryawanshi");
        dtoContact.setAddress("304/A");
        dtoContact.setCity("Thane");
        dtoContact.setState("Maharashtra");
        dtoContact.setZip("400606");
        dtoContact.setPhoneNumber("+91 9594000000");
        dtoContact.setEmail("Test1@gmail.com");

        when(addressBookService.updateContact(id, dtoContact)).thenReturn(expectedMessage);
        String actualMessage = addressBookController.updateContact(id, dtoContact);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void whenDeleteContactCalled_shouldReturnSuccessMessage() {
        String expectedMessage = "Contact Deleted Successfully";
        int id = 1;

        AddressBookDto dtoContact = new AddressBookDto();
        dtoContact.setName("Kunal Suryawanshi");
        dtoContact.setAddress("304/A");
        dtoContact.setCity("Thane");
        dtoContact.setState("Maharashtra");
        dtoContact.setZip("400606");
        dtoContact.setPhoneNumber("+91 9594000000");
        dtoContact.setEmail("Test1@gmail.com");

        when(addressBookService.deleteContactById(id)).thenReturn(expectedMessage);
        String actualMessage = addressBookController.deleteContact(id);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void givenId_whenGetContactByIdCalled_shouldReturnContactDto() {
        int id = 1;

        AddressBookDto dtoContact = new AddressBookDto();
        dtoContact.setName("Kunal Suryawanshi");
        dtoContact.setAddress("304/A");
        dtoContact.setCity("Thane");
        dtoContact.setState("Maharashtra");
        dtoContact.setZip("400606");
        dtoContact.setPhoneNumber("+91 9594000000");
        dtoContact.setEmail("Test1@gmail.com");

        when(addressBookService.getContactById(id)).thenReturn(dtoContact);
        AddressBookDto actualDto = addressBookController.getContactById(id);
        assertEquals(dtoContact, actualDto);
    }
}
