package com.example.employeemanagement.service;

import com.example.employeemanagement.model.Employee;
import com.example.employeemanagement.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        employee = Employee.builder()
                .id(1L)
                .name("Jane Doe")
                .email("jane@example.com")
                .department("HR")
                .position("Manager")
                .salary(new BigDecimal("80000.00"))
                .dateOfJoining(LocalDate.now())
                .build();
    }

    @Test
    public void testCreateEmployee() {
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee savedEmployee = employeeService.createEmployee(employee);

        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getName()).isEqualTo("Jane Doe");
    }

    @Test
    public void testGetEmployeeById_Found() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.of(employee));

        Employee foundEmployee = employeeService.getEmployeeById(1L);

        assertThat(foundEmployee).isNotNull();
        assertThat(foundEmployee.getId()).isEqualTo(1L);
    }

    @Test
    public void testGetEmployeeById_NotFound() {
        when(employeeRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> employeeService.getEmployeeById(1L));
    }
}
