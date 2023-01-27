package com.example.employee.mongoDB.controller;

import com.example.employee.mongoDB.entity.Address;
import com.example.employee.mongoDB.exception.AddressNotValidException;
import com.example.employee.mongoDB.service.AddressService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/address")
@AllArgsConstructor
public class AddressController {

    private AddressService service;

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody Address addressRequest) throws AddressNotValidException {
        var address = service.updateAddress(id, addressRequest);

        return new ResponseEntity<>(address, HttpStatus.OK);
    }
}
