package com.improvingskills.dao;

import com.improvingskills.entities.Employee;
import com.improvingskills.entities.EmployeeCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Test para operaciones Criteria
 */
public class EmployeeDAOCRITERIATest {
    EmployeeDAO dao;

    @BeforeEach
    void setUp() {
        dao = new EmployeeDAOImpl();
    }

    @Test
    void findAllCriteria() {
        List<Employee> employeeList=dao.findAllCriteria();
        System.out.println(employeeList);
    }
    @Test
    void findByIdCriteria() {
        Employee employee=dao.findByIdCriteria(2L);
        System.out.println(employee);
    }

    @Test
    void findByLastNameLikeCriteria() {
        List<Employee> employeeList=dao.findByLastNameLikeCriteria("ez");
        System.out.println(employeeList);
    }

    @Test
    void findByAgeGreaterCriteria (){
        List<Employee> employeeList=dao.findByAgeGreaterCriteria(30);
        System.out.println(employeeList);
    }

    @Test
    void findByAgeBetweenCriteria (){
        List<Employee> employeeList=dao.findByAgeBetweenCriteria(20, 30);
        System.out.println(employeeList);
    }

    @Test
    void findByAgeBetweenCriteriaAndCategory (){
        List<Employee> employeeList=dao.findByAgeBetweenCriteriaAndCategory(20, 30, EmployeeCategory.C_LEVEL);
        System.out.println(employeeList);
    }

    @Test
    void findAvgAgeCriteria(){
        Integer edad= dao.finAvgAgeCriteria();
        System.out.println("Edad promedio de empleado: "+ edad);

    }

}
