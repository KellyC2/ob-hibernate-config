package com.improvingskills.entities;
import com.improvingskills.dao.CarDAO;
import com.improvingskills.dao.CarDAOImpl;
import com.improvingskills.dao.EmployeeDAO;
import com.improvingskills.dao.EmployeeDAOImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * REVTYPE
 * 0 - INSERT
 * 1 - UPDATE
 * 2 - DELETE
 */

public class EmployeeEnversTest {
    EmployeeDAO employeeDao;
    CarDAO carDao;

    @BeforeEach
    void setUp() {
        employeeDao =new EmployeeDAOImpl();
        carDao= new CarDAOImpl();
    }

    @Test
    void createEmployee() {
        Employee employee = new Employee(null,
                "Employee Envers",
                "Makkanouchi",
                "Envers1@company.com",
                19,
                10000d,
                true,
                LocalDate.of(1996, 8, 14),
                null
        );

        employeeDao.create(employee);

        employee.setAge(28);
        employeeDao.update(employee);
        employee.setSalary(10000d);
        employeeDao.update(employee);

        Car car1=new Car(null, "Ford", 1.2, 2012);
        Car car2=new Car(null, "Toyota", 2.4, 2000);
        Car car3=new Car(null, "BWM", 1.8, 2015);

        carDao.create(car1);
        carDao.create(car2);
        carDao.create(car3);
        /*Este còdigo no logra persistir en la base de datos si tenemos una relacion bidirectional ya que el owner es car y las operaciones de persistencia debe hacerse desde car
        employee.getCars().add(car1);
        employee.getCars().add(car2);
        employee.getCars().add(car3);*/
        car1.setEmployee(employee);
        car2.setEmployee(employee);
        car3.setEmployee(employee);
        System.out.println("antes de eliminar: "+ employee.getCars());

        employee.setSalary(11000d);
        employeeDao.update(employee);

        for(Car car:employee.getCars()){
            car.setEmployee(null);
            carDao.deleteById(car.getId());
        }
        employee.getCars().clear();
        System.out.println("despues de eliminar la lista: "  + employee.getCars());

        employee.getCars().add(car3);
        employee.setSalary(12000d);
        employeeDao.update(employee);

    }
}
