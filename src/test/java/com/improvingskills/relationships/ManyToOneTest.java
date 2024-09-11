package com.improvingskills.relationships;

import com.improvingskills.dao.EmployeeDAO;
import com.improvingskills.dao.EmployeeDAOImpl;
import com.improvingskills.entities.Company;
import com.improvingskills.entities.Employee;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ManyToOneTest {
    @Test
    void manyToOneTest() {
        Employee employee1=new Employee(null,
                "Employee ManyToOne 1",
                "Perez",
                "employee1@company.com",
                50,
                8000d,
                true,
                LocalDate.of(1950,8,14),
                LocalDateTime.now()
        );
        Employee employee2=new Employee(null,
                "Employee ManyToOne 2",
                "Valbuena",
                "employee2@company.com",
                28,
                8000d,
                true,
                LocalDate.of(1996,8,14),
                LocalDateTime.now()
        );

        Company company=new Company(null, "8324242438", "COSMIC DEVELOPMENTS S.L.", 30500d, 2021);

        employee1.setCompany(company);
        employee2.setCompany(company);

        EmployeeDAO dao=new EmployeeDAOImpl();
        dao.create(employee1);
        dao.update(employee2);

        Employee employeeDB=dao.findById(1L);
        System.out.println(employeeDB.getCompany());

        //Si desde company queremos recuperar la lista de emplyees hay que hacer un findByIdEeger()

    }
}
