package com.example.employee.mongoDB.domain;

import com.example.employee.mongoDB.entity.Address;
import com.example.employee.mongoDB.entity.Contact;
import com.example.employee.mongoDB.entity.Employee;

public class EmployeeDataFactoryTest {

    public static Employee createEmployee (final Long id, final String firstName, final String lastName, final Address address, final Contact contact){
        final Employee employee = new Employee();
        employee.setId(id);
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setAddress(address);
        employee.setContact(contact);
        return employee;
    }


    public static Employee createDefaultEmployee(){
        return createEmployee(1L,  "firstName", "lastName",
                AddressDataFactoryTest.createDefaultAddress(),
                ContactDataFactoryTest.createDefaultContact());
    }



}
