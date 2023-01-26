package com.example.employee.mongoDB.controller;

import com.example.employee.mongoDB.entity.Address;
import com.example.employee.mongoDB.exception.AddressNotValidException;
import com.example.employee.mongoDB.service.AddressService;
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
@RequestMapping(path = "api/v1/address")
@AllArgsConstructor
public class AddressController {

    private AddressService service;

    @GetMapping
    public List<Address> getAllAddresses() {
        return service.getAllAddresses();
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable(name = "id") Long id) throws AddressNotValidException {
        var employee = service.findAddressById(id);

        return ResponseEntity.ok().body(employee);
    }
//
//    @PostMapping
//    public ResponseEntity<Address> registerNewAddress(@RequestBody Address address){
//        var newAddress = service.addNewAddress(address);
//
//        return new ResponseEntity<Address>(newAddress, HttpStatus.CREATED);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity deleteAddress(@PathVariable(name = "id") Long id)  throws AddressNotValidException {
//        if ( id == null) {
//            throw new AddressNotValidException("Invalid address information");
//        }
//        service.deleteAddress(id);
//        return new ResponseEntity(HttpStatus.OK);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @RequestBody Address addressRequest) throws AddressNotValidException {
        var address = service.updateAddress(id, addressRequest);

        return new ResponseEntity<>(address, HttpStatus.OK);
    }
}
