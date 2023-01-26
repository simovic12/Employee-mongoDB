package com.example.employee.mongoDB.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document
public class Contact {

    private String email;
    private String phoneNumber;
    private List<String> additionalEmails;
    private List<String> additionalPhoneNumbers;
    @DBRef
    private Employee employee;
}
