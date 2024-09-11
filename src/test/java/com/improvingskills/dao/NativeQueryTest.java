package com.improvingskills.dao;

import com.improvingskills.dto.EmployeeDTO;
import com.improvingskills.entities.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class NativeQueryTest {
    EmployeeDAO dao;

    @BeforeEach
    void setUp() {
        dao=new EmployeeDAOImpl();
    }

    @Test
    void findByIdNative() {
        Employee employee1=new Employee(null,
                "Employee ManyTomany",
                "Perez",
                "employeemanytomany@company.com",
                50,
                8000d,
                true,
                LocalDate.of(1950,8,14),
                LocalDateTime.now()
        );
        dao.create(employee1);
        Employee employee=dao.findByIdNative(1L);
        System.out.println(employee);
    }

    @Test
    void findAllNative(){
        List<Employee> employees=dao.findAllNative();
        System.out.println(employees);
    }

    @Test
    void findAllProjection(){
        List<EmployeeDTO> employees=dao.findAllProjection();
        System.out.println(employees);
    }

}
