package com.improvingskills.relationships;

import com.improvingskills.dao.DirectionDAO;
import com.improvingskills.dao.DirectionDAOImpl;
import com.improvingskills.dao.EmployeeDAO;
import com.improvingskills.dao.EmployeeDAOImpl;
import com.improvingskills.entities.Direction;
import com.improvingskills.entities.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OneToOneTest {
    @Test
    @DisplayName("Test para probar la asociación One To One entre Employee y Direction")
    void employeeDirectionTest(){
        Direction direction=new Direction(null, "ELM street", "Cansas", "Croacia");

        Employee employeeOneToOne=new Employee(null,
                "Employee OneToOne",
                "Alvarez2",
                "onetoone@company.com",
                32,
                4000d,
                true,
                LocalDate.of(1990,1,1),
                LocalDateTime.now()
        );
        employeeOneToOne.setDirection(direction);

        EmployeeDAO employeeDAO=new EmployeeDAOImpl();
        DirectionDAO directionDAO=new DirectionDAOImpl();

        directionDAO.create(direction);

        employeeDAO.create(employeeOneToOne);


        //Asegurarse recuperando de nuevo el empleado de base de datos
        Employee employeeBD=employeeDAO.findById(1l);
        System.out.println(employeeBD.getDirection());

    }
}
