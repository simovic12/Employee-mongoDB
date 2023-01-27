package com.example.employee.mongoDB.service;

import com.example.employee.mongoDB.entity.Address;
import com.example.employee.mongoDB.exception.AddressNotValidException;
import com.example.employee.mongoDB.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AddressService {

    private EmployeeRepository employeeRepository;

    public Address updateAddress(Long employeeId, Address address) throws AddressNotValidException {
        var employee = employeeRepository.findById(employeeId).orElseThrow(() -> new AddressNotValidException("Address with provided id does not exists!"));
        if (employee != null){
            employee.setAddress(address);
            employeeRepository.save(employee);
        }
        return employee.getAddress();
    }


}
