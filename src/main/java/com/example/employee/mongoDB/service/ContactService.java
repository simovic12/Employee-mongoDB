package com.example.employee.mongoDB.service;

import com.example.employee.mongoDB.entity.Contact;
import com.example.employee.mongoDB.entity.Employee;
import com.example.employee.mongoDB.exception.ContactNotValidException;
import com.example.employee.mongoDB.repository.ContactRepository;
import com.example.employee.mongoDB.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ContactService {

    private ContactRepository repository;
    private EmployeeRepository employeeRepository;

    public List<Contact> getAllContact() {
        List<Contact> contacts = new ArrayList<>();
        var employees = employeeRepository.findAll();
        for (Employee employee : employees) {
            var contact = employee.getContact();
            contacts.add(contact);
        }
        return contacts;
    }

    public Contact findContactById(Long id) throws ContactNotValidException {
        var employee = employeeRepository.findById(id).orElseThrow(() -> new ContactNotValidException("Contact with provided id does not exists!"));

        return employee.getContact();
    }

    public Contact addNewContact(Contact contact) {
        return repository.save(contact);
    }

    public void deleteContact(Long employeeId) throws ContactNotValidException {
        var contactToDelete = repository.findByEmployeeId(employeeId).orElseThrow(() -> new ContactNotValidException("Provided contact does not exists!"));
        repository.delete(contactToDelete);
    }

    public Contact updateContact(Long employeeId, Contact contact) throws ContactNotValidException {
        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ContactNotValidException("Contact you wanna update does not exists!"));
        if (employee != null) {
            employee.setContact(contact);
            employeeRepository.save(employee);
        }
        return employee.getContact();
    }
}
