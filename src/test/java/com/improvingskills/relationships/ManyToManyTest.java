package com.improvingskills.relationships;

import com.improvingskills.dao.EmployeeDAO;
import com.improvingskills.dao.EmployeeDAOImpl;
import com.improvingskills.entities.Employee;
import com.improvingskills.entities.Project;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ManyToManyTest {

    @Test
    void manyToManyTest() {
        Project project1 = new Project(null, "Project X1", LocalDate.now());
        Project project2 = new Project(null, "Project X2", LocalDate.now());

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

        employee1.getProjects().add(project1);
        employee1.getProjects().add(project2);
        EmployeeDAO dao=new EmployeeDAOImpl();
        dao.create(employee1);
    }
}
