package com.bridgelabz.addressbook.repository;

import com.bridgelabz.addressbook.entity.AddressBook;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Purpose : To Define Repository
 *
 * @author Kunal Suryawanshi
 * @Since 13-12-2021
 */
public interface AddressBookRepository extends JpaRepository<AddressBook, Integer> {
}
