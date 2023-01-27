package com.example.employee.mongoDB.controller;

import com.example.employee.mongoDB.entity.Contact;
import com.example.employee.mongoDB.exception.ContactNotValidException;
import com.example.employee.mongoDB.service.ContactService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping(path = "/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable(name = "id") Long id) throws ContactNotValidException {
        var contact = service.findContactById(id);

        return ResponseEntity.ok(contact);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable Long id, @RequestBody Contact contactRequest) throws ContactNotValidException {
        var contact = service.updateContact(id, contactRequest);

        return new ResponseEntity<>(contact, HttpStatus.OK);
    }
}
