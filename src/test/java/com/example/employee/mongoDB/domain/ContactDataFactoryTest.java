package com.example.employee.mongoDB.domain;

import com.example.employee.mongoDB.entity.Contact;

public class ContactDataFactoryTest {

    public static Contact createContact(final String email, final String phoneNumber){
        Contact contact = new Contact();
        contact.setEmail(email);
        contact.setPhoneNumber(phoneNumber);
        return contact;
    }

    public static Contact createDefaultContact(){
        return createContact("fake@fakemail.com", "phoneNumber");
    }
}
