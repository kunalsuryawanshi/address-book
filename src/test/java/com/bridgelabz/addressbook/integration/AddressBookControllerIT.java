package com.bridgelabz.addressbook.integration;

import com.bridgelabz.addressbook.controller.AddressBookController;
import com.bridgelabz.addressbook.dto.AddressBookDto;
import com.bridgelabz.addressbook.service.AddressBookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(AddressBookController.class)
public class AddressBookControllerIT {

    ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AddressBookService addressBookService;

    @Test
    void getAllAddressTest() throws Exception {
        when(addressBookService.getAllContact()).thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders.get("/addressBook/book"))
                .andExpect(status().isOk());
    }

    @Test
    void addAddressTest() throws Exception {
        AddressBookDto dtoContact = new AddressBookDto();
        dtoContact.setName("Kunal Suryawanshi");
        dtoContact.setAddress("304/A");
        dtoContact.setCity("Thane");
        dtoContact.setState("Maharashtra");
        dtoContact.setZip("400606");
        dtoContact.setPhoneNumber("+91 9594000000");
        dtoContact.setEmail("Test1@gmail.com");
        String jsonRequest = objectMapper.writeValueAsString(dtoContact);
        when(addressBookService.addContact(any())).thenReturn("success");
        mockMvc.perform(MockMvcRequestBuilders.post("/addressBook/book")
                        .content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void updateAddressTest() throws Exception {
        int id = 1;
        AddressBookDto dtoContact = new AddressBookDto();
        dtoContact.setName("Kunal Suryawanshi");
        dtoContact.setAddress("304/A");
        dtoContact.setCity("Thane");
        dtoContact.setState("Maharashtra");
        dtoContact.setZip("400606");
        dtoContact.setPhoneNumber("+91 9594000000");
        dtoContact.setEmail("Test1@gmail.com");

        String jsonRequest = objectMapper.writeValueAsString(dtoContact);
        when(addressBookService.updateContact(id, dtoContact))
                .thenReturn("Success");
        mockMvc.perform(MockMvcRequestBuilders.put("/addressBook/book/1")
                        .content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void deleteAddressTest() throws Exception {
        int id = 1;
        when(addressBookService.deleteContactById(id)).thenReturn("Success");
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/addressBook/book/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getAddressTest() throws Exception {
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
        mockMvc.perform(MockMvcRequestBuilders
                .get("/addressBook/book/1")).andExpect(status().isOk());
    }
}
