package com.example.employee.mongoDB.service;

import com.example.employee.mongoDB.entity.Contact;
import com.example.employee.mongoDB.exception.ContactNotValidException;
import com.example.employee.mongoDB.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ContactService {

    private EmployeeRepository repository;

    public Contact updateContact(Long employeeId, Contact contact) throws ContactNotValidException {
        var employee = repository.findById(employeeId).orElseThrow(() -> new ContactNotValidException("Contact with provided id " + employeeId + " does not exists!"));
        if (employee != null){
            employee.setContact(contact);
            repository.save(employee);
        }
        return employee.getContact();
    }
}
