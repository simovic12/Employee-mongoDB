package com.example.employee.mongoDB.service;

import com.example.employee.mongoDB.entity.Employee;
import com.example.employee.mongoDB.exception.EmployeeNotValidException;
import com.example.employee.mongoDB.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {

    private EmployeeRepository repository;

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    public Employee findEmployeeById(Long id) throws EmployeeNotValidException {
        var employee = repository.findById(id).orElseThrow(() -> new EmployeeNotValidException("Employee does not exists!"));
        return employee;
    }

    public Employee addNewEmployee(Employee employee) {
        if (employee.getId() == null) {
            return null;
        }

        var newEmployee = repository.insert(employee);
        return newEmployee;
    }

    public void deleteEmployee(Long id) throws EmployeeNotValidException {
        var employeeToDelete = repository.findById(id).orElseThrow(() -> new EmployeeNotValidException("Provided employee does not exists!"));
        repository.delete(employeeToDelete);
    }

    public Employee updateEmployee(Employee employee) {
        return repository.save(employee);
    }
}
