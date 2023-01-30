package com.example.employee.mongoDB.controller;

import com.example.employee.mongoDB.entity.Employee;
import com.example.employee.mongoDB.exception.EmployeeNotValidException;
import com.example.employee.mongoDB.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/employee")
@AllArgsConstructor
public class EmployeeController {

    private EmployeeService service;

    @GetMapping
    @Cacheable(value = "employees")
    public List<Employee> getAllEmployees() {
        return service.getAllEmployees();
    }

    @GetMapping(path = "/{id}")
    @Cacheable(value = "employees", key = "#id")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable(name = "id") Long id) throws EmployeeNotValidException {
        var employee = service.findEmployeeById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Employee> registerNewEmployee(@RequestBody Employee employee) {
        var newEmployee = service.addNewEmployee(employee);

        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "employees", key = "#id")
    public ResponseEntity deleteEmployee(@PathVariable(name = "id") Long id) throws EmployeeNotValidException {
        service.deleteEmployee(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @CachePut(value = "employees", key = "#employee.id")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        service.updateEmployee(employee);
        return ResponseEntity.ok(employee);
    }
}
