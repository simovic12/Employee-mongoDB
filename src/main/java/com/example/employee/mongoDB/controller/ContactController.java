package com.example.employee.mongoDB.controller;

import com.example.employee.mongoDB.entity.Contact;
import com.example.employee.mongoDB.exception.AddressNotValidException;
import com.example.employee.mongoDB.exception.ContactNotValidException;
import com.example.employee.mongoDB.service.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/contact")
@AllArgsConstructor
public class ContactController {

    private ContactService service;

    @PutMapping(path = "/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable Long id, @RequestBody Contact contactRequest) throws ContactNotValidException, AddressNotValidException {
        var contact = service.updateContact(id, contactRequest);

        return new ResponseEntity<>(contact, HttpStatus.OK);
    }
}
