package com.improvingskills.dao;

import com.improvingskills.entities.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Test para las operaciones CRUD
 */
class EmployeeDAOCRUDTest {

    EmployeeDAO dao;

    @BeforeEach
    void setUp() {
        dao=new EmployeeDAOImpl();
    }

    @Test
    void findAll() {
        List<Employee> employees=dao.findAll();
        System.out.println(employees);
    }

    @Test
    void findById() {
        Employee employee1=dao.findById(1L);
        Employee employee2=dao.findById(2L);
        Employee employee3=dao.findById(3L);
    }

    @Test
    void findByAge() {
        List<Employee> employee20=dao.findByAge(20);
        List<Employee> employee32=dao.findByAge(32);
        List<Employee> employee50=dao.findByAge(50);

    }

    @Test
    void create() {
        Employee employee=new Employee(null, "Empleado creado desde Junit", "Tesy", "test@test.com", 18,13500d,false, LocalDate.now(), LocalDateTime.now());
        Employee employee3=new Employee(null,
                "Employee3",
                "Gutierrez",
                "employee3@company.com",
                30,
                8000d,
                true,
                LocalDate.of(1950,8,14),
                LocalDateTime.now()
        );
        Employee employee4=new Employee(null,
                "Employee4",
                "Valdez",
                "employee4@company.com",
                29,
                10000d,
                true,
                LocalDate.of(1950,8,14),
                LocalDateTime.now()
        );
        Employee employee5=new Employee(null,
                "Employee5",
                "Alvarado",
                "employee5@company.com",
                29,
                50000d,
                true,
                LocalDate.of(1950,8,14),
                LocalDateTime.now()
        );
        employee=dao.create(employee);
        employee3=dao.create(employee3);
        employee4=dao.create(employee4);
        employee5=dao.create(employee5);
        System.out.println(employee);

    }

    @Test
    void update() {
        Employee employee3=new Employee(3L,
                "Empleado creado desde Junit",
                "Modificado con el metodo update",
                "test@test.com",
                18,
                18500d,
                false,
                LocalDate.of(2003,6,15),
                LocalDateTime.now());
        employee3=dao.update(employee3);
        System.out.println(employee3);
    }

    @Test
    void deleteById() {
       boolean result= dao.deleteById(1L);
        System.out.println(result);
    }

    @Test
    void countTest(){
        Long employeeNumbers=dao.count();
        System.out.println("El número de empleados es: "+ employeeNumbers);
    }
}