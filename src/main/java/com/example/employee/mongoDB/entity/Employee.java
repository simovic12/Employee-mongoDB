package com.example.employee.mongoDB.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Employee {

    @Id
    private Long id;
    private String firstName;
    private String LastName;
    private Address address;
    private Contact contact;

}
