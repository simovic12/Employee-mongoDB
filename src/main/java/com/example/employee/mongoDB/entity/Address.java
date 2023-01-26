package com.example.employee.mongoDB.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Address {

    private String street;
    private String city;
    private String postNumber;
    @DBRef
    private Employee employee;
}
