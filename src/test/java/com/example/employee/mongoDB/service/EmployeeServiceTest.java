package com.example.employee.mongoDB.service;

import com.example.employee.mongoDB.domain.EmployeeDataFactoryTest;
import com.example.employee.mongoDB.entity.Employee;
import com.example.employee.mongoDB.exception.EmployeeNotValidException;
import com.example.employee.mongoDB.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @InjectMocks
    private EmployeeService service;

    @Mock
    private EmployeeRepository repository;

    @Test
    @DisplayName(value = "Employee test: findAll")
    public void should_getAllEmployees_test(){
        final List<Employee> employees = service.getAllEmployees();
        Assertions.assertNotNull(employees);
    }

    @Test
    @DisplayName(value = "Employee test: findById")
    public void should_findEmployeeById_test() {
        Employee employee = EmployeeDataFactoryTest.createDefaultEmployee();

        repository.findById(1L);
        Assertions.assertNotNull(employee);
    }

    @Test
    @DisplayName(value = "Employee test: create")
    public void should_createEmployee_test(){
        Employee employee = EmployeeDataFactoryTest.createDefaultEmployee();
        when(repository.save(any())).thenReturn(employee);
        Employee savedEmployee = service.addNewEmployee(employee);

        Assertions.assertNotNull(savedEmployee);
        Assertions.assertEquals(employee.getId(), savedEmployee.getId());
        Assertions.assertEquals(employee.getFirstName(), savedEmployee.getFirstName());
        Assertions.assertEquals(employee.getLastName(), savedEmployee.getLastName());
        Assertions.assertEquals(employee.getAddress(), savedEmployee.getAddress());
        Assertions.assertEquals(employee.getContact(), savedEmployee.getContact());

        verify(repository).save(any());
        verifyNoMoreInteractions(repository);

    }

    @Test
    @DisplayName(value = "Employee test: update")
    public void should_updateEmployee_test() throws EmployeeNotValidException {
        Employee employee = EmployeeDataFactoryTest.createDefaultEmployee();

        when(repository.findById(any())).thenReturn(Optional.of(employee));
        when(repository.save(any())).thenReturn(employee);

        Employee employeeToUpdate = service.findEmployeeById(1L);
        service.updateEmployee(employeeToUpdate);

        verify(repository).findById(1L);
        verify(repository).save(employee);
        verifyNoMoreInteractions(repository);
    }

    @Test
    @DisplayName(value = "Employee test: delete")
    public void should_deleteEmployee_test() throws EmployeeNotValidException {
        Employee employee = EmployeeDataFactoryTest.createDefaultEmployee();
        repository.save(employee);

        repository.deleteById(employee.getId());
        Optional<Employee> employeeOptional = repository.findById(employee.getId());

        assertThat(employeeOptional).isEmpty();

    }
}
