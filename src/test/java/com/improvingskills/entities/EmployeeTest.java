package com.improvingskills.entities;

import com.improvingskills.dao.DirectionDAO;
import com.improvingskills.dao.DirectionDAOImpl;
import com.improvingskills.dao.EmployeeDAO;
import com.improvingskills.dao.EmployeeDAOImpl;
import com.improvingskills.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EmployeeTest {
    @Test
    void createTablesTest(){
        Employee employee1=new Employee(null,
                "Employee1",
                "García",
                "employee1@company.com",
                32,
                4000d,
                true,
                LocalDate.of(1990,1,1),
                LocalDateTime.now()
        );
        Employee employee2=new Employee(null,
                "Employee2",
                "Perez",
                "employee2@company.com",
                50,
                8000d,
                true,
                LocalDate.of(1950,8,14),
                LocalDateTime.now()
        );

        SessionFactory sessionFactory= HibernateUtil.getSessionFactory();
        Session session=sessionFactory.openSession();

        session.beginTransaction();

        session.persist(employee1);
        session.persist(employee2);

        session.getTransaction().commit();

        session.close();
        sessionFactory.close();
        HibernateUtil.shutdown();
    }

    @Test
    void nickNameTest() {
        Employee employee=new Employee(null,
                "Employee Junit Nicknames",
                "Condori",
                "colecciones@company.com",
                29,
                10000d,
                true,
                LocalDate.of(1996,8,14),
                LocalDateTime.now()
        );
        /*OPCIÓN 1:
        List<String> nickNames=new ArrayList<>();
        nickNames.add("nickName1");
        nickNames.add("nickName2");
        nickNames.add("nickName3");
        nickNames.add("nickName4");*/

        //OPCIÓN 2:
        employee.getNickname().add("nickName1");
        employee.getNickname().add("nickName2");
        employee.getNickname().add("nickName3");
        employee.getNickname().add("nickName4");

        employee.getPostalCodes().add(33010);
        employee.getPostalCodes().add(88010);
        employee.getPostalCodes().add(21040);
        employee.getPostalCodes().add(99610);

        employee.getCreditCards().add("4122 45563 4899");
        employee.getCreditCards().add("4122 45563 4977");
        employee.getCreditCards().add("4122 45563 4102");
        employee.getCreditCards().add("4122 45563 4856");

        employee.getPhones().put("658241756", "entel");
        employee.getPhones().put("656981756", "bitel");
        employee.getPhones().put("517958636", "movistar");
        employee.getPhones().put("874211756", "claro");

        employee.setCategory(EmployeeCategory.C_LEVEL);

        EmployeeDAO dao=new EmployeeDAOImpl();
        dao.update(employee);
    }


}
