package com.improvingskills.relationships;

import com.improvingskills.dao.EmployeeDAO;
import com.improvingskills.dao.EmployeeDAOImpl;
import com.improvingskills.entities.Car;
import com.improvingskills.entities.Employee;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class OneToManyTest {
    @Test
    void oneToManyTest(){
        Employee employeeOneToMany=new Employee(null,
                "Employee OneToMany",
                "Martinez",
                "onetomany@company.com",
                30,
                5000d,
                true,
                LocalDate.of(1995,1,1),
                LocalDateTime.now()
        );

        Car car1=new Car(null, "Ford", 1.2, 2012);
        Car car2=new Car(null, "Toyota", 2.4, 2000);
        Car car3=new Car(null, "BWM", 1.8, 2015);

        employeeOneToMany.getCars().add(car1);
        employeeOneToMany.getCars().add(car2);
        employeeOneToMany.getCars().add(car3);

        EmployeeDAO edao=new EmployeeDAOImpl();
        edao.create(employeeOneToMany);


        //org.hibenate.lazyInitializationExceptio: failed to lazy initilize a collection of role: ....
        //Employee employeeDB= edao.findById(1L);
        //System.out.println(employeeDB);
        //List<Car> cars=employeeDB.getCars();
        //System.out.println(cars);

        //Para evitar LazyInitializationException creamos queries especificos que carguen los datos que queramos
        Employee employeeDB=edao.findByIdEager(1L);
        System.out.println(employeeDB);
        List<Car> cars=employeeDB.getCars();
        System.out.println(cars);
    }
}
