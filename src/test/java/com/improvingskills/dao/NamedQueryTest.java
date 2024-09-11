package com.improvingskills.dao;

import com.improvingskills.entities.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class NamedQueryTest {
    EmployeeDAO dao;

    @BeforeEach
    void setUp() {
        dao=new EmployeeDAOImpl();
    }

    @Test
    void findMostPaid(){
        List<Employee> employees=dao.findMostPaid();
        System.out.println(employees);
    }
}
