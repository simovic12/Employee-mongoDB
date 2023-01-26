package com.example.employee.mongoDB.service;

import com.example.employee.mongoDB.entity.Address;
import com.example.employee.mongoDB.entity.Employee;
import com.example.employee.mongoDB.exception.AddressNotValidException;
import com.example.employee.mongoDB.repository.AddressRepository;
import com.example.employee.mongoDB.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class AddressService {

    private AddressRepository repository;
    private EmployeeRepository employeeRepository;

    public List<Address> getAllAddresses() {
        List<Address> addresses = new ArrayList<>();
        var employees = employeeRepository.findAll();
        for (Employee employee : employees) {
            Address address = employee.getAddress();
            addresses.add(address);
        }

        return addresses;
    }

    public Address findAddressById(Long id) throws AddressNotValidException {
        var employee = employeeRepository.findById(id).orElseThrow(() -> new AddressNotValidException("Address with provided id does not exists!"));
        return employee.getAddress();
    }

    public void deleteAddress(Long employeesId) throws AddressNotValidException {
        var addressToDelete = repository.findByEmployeeId(employeesId).orElseThrow(() -> new AddressNotValidException("Address does not exists!"));
        repository.delete(addressToDelete);
    }

    public Address addNewAddress(Address address) {
        return repository.save(address);
    }

    public Address updateAddress(Long employeeId, Address address) throws AddressNotValidException {
        var employee = employeeRepository.findById(employeeId).orElseThrow(() -> new AddressNotValidException("Address with provided id does not exists!"));
        if (employee != null){
            employee.setAddress(address);
            employeeRepository.save(employee);
        }
        return employee.getAddress();
    }


}
