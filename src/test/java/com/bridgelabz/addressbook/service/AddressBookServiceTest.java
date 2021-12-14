package com.bridgelabz.addressbook.service;

import com.bridgelabz.addressbook.dto.AddressBookDto;
import com.bridgelabz.addressbook.entity.AddressBook;
import com.bridgelabz.addressbook.repository.AddressBookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddressBookServiceTest {

    @InjectMocks
    private AddressBookService addressBookService;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private AddressBookRepository addressBookRepository;

    @Test
    void whenGetAllMethodCalled_shouldReturnListOfContacts() {
        List<AddressBook> addressBookEntityList = new ArrayList<>();

        AddressBook contact1 = new AddressBook();
        contact1.setId(1);
        contact1.setName("Kunal Suryawanshi");
        contact1.setAddress("304/A");
        contact1.setCity("Thane");
        contact1.setState("Maharashtra");
        contact1.setZip("400606");
        contact1.setPhoneNumber("+91 9594000000");
        contact1.setEmail("Test1@gmail.com");
        contact1.setCreatedOn(LocalDateTime.now());
        contact1.setUpdatedOn(LocalDateTime.now());
        addressBookEntityList.add(contact1);

        AddressBook contact2 = new AddressBook();
        contact1.setId(1);
        contact1.setName("Pratik Shinde");
        contact1.setAddress("304/A");
        contact1.setCity("Thane");
        contact1.setState("Maharashtra");
        contact1.setZip("400606");
        contact1.setPhoneNumber("+91 9000000000");
        contact1.setEmail("Test2@gmail.com");
        addressBookEntityList.add(contact2);

        List<AddressBookDto> addressBookDtoList = new ArrayList<>();

        AddressBookDto dtoContact1 = new AddressBookDto();
        dtoContact1.setName(contact1.getName());
        dtoContact1.setAddress(contact1.getAddress());
        dtoContact1.setCity(contact1.getCity());
        dtoContact1.setState(contact1.getState());
        dtoContact1.setZip(contact1.getZip());
        dtoContact1.setPhoneNumber(contact1.getPhoneNumber());
        dtoContact1.setEmail(contact1.getEmail());
        addressBookDtoList.add(dtoContact1);

        AddressBookDto dtoContact2 = new AddressBookDto();
        dtoContact2.setName(contact2.getName());
        dtoContact2.setAddress(contact2.getAddress());
        dtoContact2.setCity(contact2.getCity());
        dtoContact2.setState(contact2.getState());
        dtoContact2.setZip(contact2.getZip());
        dtoContact2.setPhoneNumber(contact2.getPhoneNumber());
        dtoContact2.setEmail(contact2.getEmail());
        addressBookDtoList.add(dtoContact2);

        when(addressBookRepository.findAll()).thenReturn(addressBookEntityList);
        when(modelMapper.map(addressBookEntityList.get(0),
                AddressBookDto.class)).thenReturn(dtoContact1);
        when(modelMapper.map(addressBookEntityList.get(1),
                AddressBookDto.class)).thenReturn(dtoContact2);
        List<AddressBookDto> actualResponse = addressBookService.getAllContact();
        assertEquals(2, actualResponse.size());
        assertEquals(addressBookDtoList, actualResponse);
    }

    @Test
    void whenAddMethodCalled_shouldReturnSuccessMessage() {
        String expectedMessage = "Contact Added Successfully";

        AddressBookDto dtoContact1 = new AddressBookDto();
        dtoContact1.setName("Kunal Suryawanshi");
        dtoContact1.setAddress("304/A");
        dtoContact1.setCity("Thane");
        dtoContact1.setState("Maharashtra");
        dtoContact1.setZip("400606");
        dtoContact1.setPhoneNumber("+91 9594000000");
        dtoContact1.setEmail("Test1@gmail.com");

        AddressBook contact1 = new AddressBook();
        contact1.setId(1);
        contact1.setName(dtoContact1.getName());
        contact1.setAddress(dtoContact1.getAddress());
        contact1.setCity(dtoContact1.getCity());
        contact1.setState(dtoContact1.getState());
        contact1.setZip(dtoContact1.getZip());
        contact1.setPhoneNumber(dtoContact1.getPhoneNumber());
        contact1.setEmail(dtoContact1.getEmail());
        contact1.setCreatedOn(LocalDateTime.now());
        contact1.setUpdatedOn(LocalDateTime.now());

        when(modelMapper.map(dtoContact1, AddressBook.class))
                .thenReturn(contact1);
        String actualMessage = addressBookService.addContact(dtoContact1);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void whenUpdateContactCalled_shouldReturnSuccessMessage() {
        ArgumentCaptor<AddressBook> addressBookArgumentCaptor = ArgumentCaptor.forClass(AddressBook.class);
        String expectedMessage = "Contact Updated Successfully";

        int id = 1;
        AddressBookDto dtoContact1 = new AddressBookDto();
        dtoContact1.setName("Kunal Suryawanshi");
        dtoContact1.setAddress("304/A");
        dtoContact1.setCity("Thane");
        dtoContact1.setState("Maharashtra");
        dtoContact1.setZip("400606");
        dtoContact1.setPhoneNumber("+91 9594000000");
        dtoContact1.setEmail("Test1@gmail.com");

        AddressBook contact1 = new AddressBook();
        contact1.setId(id);
        contact1.setName("Pratik Shinde");
        contact1.setAddress("304/A");
        contact1.setCity("Thane");
        contact1.setState("Maharashtra");
        contact1.setZip("400606");
        contact1.setPhoneNumber("+91 9594000000");
        contact1.setEmail("Test1@gmail.com");
        contact1.setCreatedOn(LocalDateTime.now());
        contact1.setUpdatedOn(LocalDateTime.now());

        when(addressBookRepository.findById(id)).thenReturn(Optional.of(contact1));
        contact1.setId(id);
        contact1.setName(dtoContact1.getName());
        contact1.setAddress(dtoContact1.getAddress());
        contact1.setCity(dtoContact1.getCity());
        contact1.setState(dtoContact1.getState());
        contact1.setZip(dtoContact1.getZip());
        contact1.setPhoneNumber(dtoContact1.getPhoneNumber());
        contact1.setEmail(dtoContact1.getEmail());
        String actualMessage = addressBookService.updateContact(id, dtoContact1);
        verify(addressBookRepository, times(1))
                .save(addressBookArgumentCaptor.capture());
        assertEquals(expectedMessage, actualMessage);
        assertEquals(dtoContact1.getName(), addressBookArgumentCaptor.getValue().getName());
        assertEquals(dtoContact1.getAddress(), addressBookArgumentCaptor.getValue().getAddress());
        assertEquals(dtoContact1.getCity(), addressBookArgumentCaptor.getValue().getCity());
        assertEquals(dtoContact1.getState(), addressBookArgumentCaptor.getValue().getState());
        assertEquals(dtoContact1.getZip(), addressBookArgumentCaptor.getValue().getZip());
        assertEquals(dtoContact1.getPhoneNumber(), addressBookArgumentCaptor.getValue().getPhoneNumber());
        assertEquals(dtoContact1.getEmail(), addressBookArgumentCaptor.getValue().getEmail());
    }

    @Test
    void givenId_whenNotFound_shouldThrowException() {
        int id = 1;
        when(addressBookRepository.findById(id)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> addressBookService.checkIdPresentOrNot(id));
    }

    @Test
    void whenDeleteContactCalled_shouldReturnSuccessMessage() {
        int id = 1;
        String expectedMessage = "Contact Deleted Successfully";

        AddressBookDto dtoContact1 = new AddressBookDto();
        dtoContact1.setName("Kunal Suryawanshi");
        dtoContact1.setAddress("304/A");
        dtoContact1.setCity("Thane");
        dtoContact1.setState("Maharashtra");
        dtoContact1.setZip("400606");
        dtoContact1.setPhoneNumber("+91 9594000000");
        dtoContact1.setEmail("Test1@gmail.com");

        AddressBook contact1 = new AddressBook();
        contact1.setId(id);
        contact1.setName(dtoContact1.getName());
        contact1.setAddress(dtoContact1.getAddress());
        contact1.setCity(dtoContact1.getCity());
        contact1.setState(dtoContact1.getState());
        contact1.setZip(dtoContact1.getZip());
        contact1.setPhoneNumber(dtoContact1.getPhoneNumber());
        contact1.setEmail(dtoContact1.getEmail());

        when(addressBookRepository.findById(id)).thenReturn(Optional.of(contact1));
        String actualMessage = addressBookService.deleteContactById(id);
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void whenGetEmployeeByIdCalled_shouldReturnContactDto() {
        int id = 1;

        AddressBookDto dtoContact1 = new AddressBookDto();
        dtoContact1.setName("Kunal Suryawanshi");
        dtoContact1.setAddress("304/A");
        dtoContact1.setCity("Thane");
        dtoContact1.setState("Maharashtra");
        dtoContact1.setZip("400606");
        dtoContact1.setPhoneNumber("+91 9594000000");
        dtoContact1.setEmail("Test1@gmail.com");

        AddressBook contact1 = new AddressBook();
        contact1.setId(id);
        contact1.setName("Kunal Suryawanshi");
        contact1.setAddress("304/A");
        contact1.setCity("Thane");
        contact1.setState("Maharashtra");
        contact1.setZip("400606");
        contact1.setPhoneNumber("+91 9594000000");
        contact1.setEmail("Test1@gmail.com");
        contact1.setCreatedOn(LocalDateTime.now());
        contact1.setUpdatedOn(LocalDateTime.now());

        when(addressBookRepository.findById(id)).thenReturn(Optional.of(contact1));
        when(modelMapper.map(contact1, AddressBookDto.class))
                .thenReturn(dtoContact1);
        AddressBookDto addressBookDto = addressBookService.getContactById(id);
        assertEquals(dtoContact1, addressBookDto);
    }
}


