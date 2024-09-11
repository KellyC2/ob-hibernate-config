package com.improvingskills.entities;

import com.improvingskills.dao.CarDAO;
import com.improvingskills.dao.CarDAOImpl;
import com.improvingskills.dao.EmployeeDAO;
import com.improvingskills.dao.EmployeeDAOImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class EmployeeEventsTest {
    EmployeeDAO employeeDao;
    CarDAO carDao;

    @BeforeEach
    void setUp() {
        employeeDao =new EmployeeDAOImpl();
        carDao= new CarDAOImpl();
    }

    @Test
    void preEventsTest(){
        Employee employee=new Employee(null,
                "Employee prueba PrePersist",
                "Makkanouchi",
                "prepersist@company.com",
                19,
                10000d,
                true,
                LocalDate.of(1996,8,14),
                null
        );

        employeeDao.create(employee);

        /*
        Verficiar en los logs que se invoca el método prepersistir y se asigna una fecha al campo registerDate.
         */


    }

    @Test
    void preUpdate(){
        Employee employee= employeeDao.findById(1L);
        employee.setAge(50);
        employee.setCategory(EmployeeCategory.SENIOR);
        employeeDao.update(employee);

        /*
        Verificar en los logs qeu se ha ejecutado el metodo preUpdate()
         */
    }

    @Test
    void preRemoveEmployee(){
        Employee employee=new Employee(null,
                "Employee event",
                "Martinez",
                "removeEvent@company.com",
                30,
                5000d,
                true,
                LocalDate.of(1995,1,1),
                null
        );

        Car car1=new Car(null, "Ford", 1.2, 2012);
        Car car2=new Car(null, "Toyota", 2.4, 2000);
        Car car3=new Car(null, "BWM", 1.8, 2015);

        carDao.create(car1);
        carDao.create(car2);
        carDao.create(car3);

        employee.getCars().add(car1);
        employee.getCars().add(car2);
        employee.getCars().add(car3);


        employeeDao.create(employee);

        employeeDao.deleteById(employee.getId());

    }

    @Test
    void preRemoveCar(){
        Employee employee=new Employee(null,
                "Employee event",
                "Martinez",
                "removeEvent@company.com",
                30,
                5000d,
                true,
                LocalDate.of(1995,1,1),
                null
        );

        Car car1=new Car(null, "Ford", 1.2, 2012);
        Car car2=new Car(null, "Toyota", 2.4, 2000);
        Car car3=new Car(null, "BWM", 1.8, 2015);

        carDao.create(car1);
        carDao.create(car2);
        carDao.create(car3);

        employee.getCars().add(car1);
        employee.getCars().add(car2);
        employee.getCars().add(car3);

        employeeDao.create(employee);

        carDao.deleteById(car1.getId());

    }
}
