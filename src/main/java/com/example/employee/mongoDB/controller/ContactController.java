package com.example.employee.mongoDB.controller;

import com.example.employee.mongoDB.entity.Contact;
import com.example.employee.mongoDB.exception.ContactNotValidException;
import com.example.employee.mongoDB.exception.EmployeeNotValidException;
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

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/contact")
@AllArgsConstructor
public class ContactController {

    private ContactService service;

    @GetMapping
    public List<Contact> getAllContacts() {
        return service.getAllContact();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable(name = "id") Long id) throws EmployeeNotValidException, ContactNotValidException {
        var contact = service.findContactById(id);

        return ResponseEntity.ok(contact);
    }

//    @PostMapping
//    public ResponseEntity<Contact> registerNewContact(@RequestBody Contact contactRequest) throws EmailAlreadyExistsException, PhoneNumberAlreadyExistsException {
//       var contact = service.addNewContact(contactRequest);
//
//       return new ResponseEntity<>(contact, HttpStatus.CREATED);
//    }
//
//    @DeleteMapping(name = "/{id}")
//    public ResponseEntity deleteContact(@PathVariable(name = "id") Long employeesId,  Contact contact) throws ContactNotValidException {
//        service.deleteContact(employeesId);
//        return new ResponseEntity(HttpStatus.OK);
//    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable Long id, @RequestBody Contact contactRequest) throws ContactNotValidException {
        var contact = service.updateContact(id, contactRequest);

        return new ResponseEntity<>(contact, HttpStatus.OK);
    }
}
