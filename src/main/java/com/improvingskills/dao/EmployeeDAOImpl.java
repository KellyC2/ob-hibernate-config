package com.improvingskills.dao;

import com.improvingskills.dto.EmployeeDTO;
import com.improvingskills.entities.Employee;
import com.improvingskills.entities.EmployeeCategory;
import com.improvingskills.util.HibernateUtil;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public List<Employee> findAll() {
        Session session= HibernateUtil.getSessionFactory().openSession();

        //Consulta HQL
        Query<Employee> query=session.createQuery("from Employee", Employee.class);
        List<Employee> employees=query.list();

        /*Equivalente
        List<Employee> employees=session.createQuery("from Employee", Employee.class).list();*/

        session.close();
        return employees;
    }

    @Override
    public List<Employee> findAllCriteria() {
        Session session=HibernateUtil.getSessionFactory().openSession();
        //1. Criteria
        CriteriaBuilder builder=session.getCriteriaBuilder();
        CriteriaQuery <Employee> query=builder.createQuery(Employee.class);
        Root<Employee> root=query.from(Employee.class);
        query.select(root);

        //2. Query
        List<Employee> employees=session.createQuery(query).list();

        session.close();
        return employees;

    }

    @Override
    public List<Employee> findAllNative() {
        Session session=HibernateUtil.getSessionFactory().openSession();
        List<Employee> employees =session.createNativeQuery("SELECT * FROM ob_employees", Employee.class).list();

        session.close();
        return employees;
    }

    @Override
    public List<EmployeeDTO> findAllProjection() {
        Session session=HibernateUtil.getSessionFactory().openSession();

        List<EmployeeDTO> employeeDTOS=new ArrayList<>();

        List<Object[]> employees=session.createNativeQuery("SELECT id, email from ob_employees").list();

        for(Object[] employee:employees){
            Long id=(Long) employee[0];
            String email=(String) employee[1];
            employeeDTOS.add(new EmployeeDTO(id,email));
        }
        session.close();
        return employeeDTOS;
    }

    @Override
    public List<Employee> findMostPaid() {
        Session session=HibernateUtil.getSessionFactory().openSession();
        List<Employee> employees=session.createNamedQuery("Employee.mostPaid",Employee.class).list();
        session.close();
        return employees;
    }

    @Override
    public Long count() {
        Session session=HibernateUtil.getSessionFactory().openSession();

        Long count= (Long) session.createQuery("select count(e) from Employee e").getSingleResult();

        session.close();
        return count;
    }

    @Override
    public Employee findById(Long id) {
        Session session=HibernateUtil.getSessionFactory().openSession();
        Employee employee=session.find(Employee.class, id);
        session.close();
        return employee;
    }

    @Override
    public Employee findByIdEager(Long id) {
        Session session=HibernateUtil.getSessionFactory().openSession();

        Query<Employee> query=session.createQuery("select distinct e from Employee e join fetch e.cars where e.id= :pk", Employee.class);
        query.setParameter("pk", id);

        Employee employee=query.getSingleResult();

        session.close();
        return employee;
    }

    @Override
    public Employee findByIdCriteria(Long id) {
        Session session=HibernateUtil.getSessionFactory().openSession();
        //Criteria
        CriteriaBuilder builder=session.getCriteriaBuilder();
        CriteriaQuery<Employee> query=builder.createQuery(Employee.class);
        Root<Employee> root=query.from(Employee.class);
        Predicate filter=builder.equal(root.get("id"),id);
        query.select(root).where(filter);

        //Ejecutando query
        Employee employee=session.createQuery(query).getSingleResult();

        session.close();

        return employee;

    }

    @Override
    public Employee findByIdNative(Long id) {
        Session session=HibernateUtil.getSessionFactory().openSession();
        NativeQuery<Employee> query = session.createNativeQuery("select * from ob_employees where id= :id", Employee.class);
        query.setParameter("id",id);

        Employee employee= query.getSingleResult();
        session.close();
        return employee;
    }

    @Override
    public List<Employee> findByAge(Integer age) {
        Session session=HibernateUtil.getSessionFactory().openSession();
        Query<Employee> query=session.createQuery("from Employee where age= :age",Employee.class);
        query.setParameter("age",age);
        List<Employee> employees=query.list();

        session.close();
        return employees;
    }

    /**
     * Operación de agregación
     * @return
     */
    @Override
    public Integer finAvgAgeCriteria() {
        Session session=HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder builder=session.getCriteriaBuilder();
        CriteriaQuery<Double> criteria=builder.createQuery(Double.class);
        Root<Employee> root =criteria.from(Employee.class);

        Expression<Double> avg=builder.avg(root.get("age"));
        criteria.select(avg);

        //Query:
        Double avgAge=session.createQuery(criteria).getSingleResult();

        session.close();
        return (int)Math.round(avgAge);

    }

    @Override
    public List<Employee> findByLastNameLikeCriteria(String lastName) {
        //Equivalente a contains
        //SELECT *FROM ob_employees WHERE lastName like "%ence%"
        Session session=HibernateUtil.getSessionFactory().openSession();
        //Criteria
        CriteriaBuilder builder=session.getCriteriaBuilder();
        CriteriaQuery<Employee> query=builder.createQuery(Employee.class);
        Root<Employee> root=query.from(Employee.class);
        Predicate filter=builder.like(root.get("lastName"),"%"+lastName+"%");
        query.select(root).where(filter);

        //Ejecutando query
        List <Employee> employees=session.createQuery(query).list();

        session.close();

        return employees;
    }

    @Override
    public List<Employee> findByAgeGreaterCriteria(Integer age) {

        Session session=HibernateUtil.getSessionFactory().openSession();
        //Criteria
        CriteriaBuilder builder=session.getCriteriaBuilder();
        CriteriaQuery<Employee> query=builder.createQuery(Employee.class);
        Root<Employee> root=query.from(Employee.class);
        Predicate filter=builder.gt(root.get("age"),age);
        query.select(root).where(filter);

        //Ejecutando query
        List <Employee> employees=session.createQuery(query).list();

        session.close();

        return employees;
    }

    @Override
    public List<Employee> findByAgeBetweenCriteria(Integer min, Integer max) {

        Session session=HibernateUtil.getSessionFactory().openSession();
        //Criteria
        CriteriaBuilder builder=session.getCriteriaBuilder();
        CriteriaQuery<Employee> query=builder.createQuery(Employee.class);
        Root<Employee> root=query.from(Employee.class);
        Predicate filter=builder.between(root.get("age"),min,max);
        query.select(root).where(filter);

        //Ejecutando query
        List <Employee> employees=session.createQuery(query).list();

        session.close();

        return employees;
    }

    @Override
    public List<Employee> findByAgeBetweenCriteriaAndCategory(Integer ageMin, Integer ageMax, EmployeeCategory category) {
        Session session=HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder builder=session.getCriteriaBuilder();//Creamos un constructor de predicados o filtros.
        CriteriaQuery<Employee> criteria=builder.createQuery(Employee.class);//Crea una consulta dinámica(de tipo CriteriaQuery) para recuperar instancias de cla clase Employee desde la base de datos utilizando el API de Criteria de JPA/Hibernate
        Root<Employee> root=criteria.from(Employee.class);//Crea la base para la contruccion de la consulta. Actúa como el punto de inicio desde donde se puede definir que columnas o atributos de la entidad Employee se van a consular o filtrar.

        Predicate agefilter=builder.between(root.get("age"), ageMin, ageMax);
        Predicate categoryFilter=builder.equal(root.get("category"), category);
        Predicate filter=builder.and(agefilter,categoryFilter);//AND
        //Predicate filter=builder.or(agefilter,categoryFilter);//OR

        criteria.select(root).where(filter);

        List<Employee>employees=session.createQuery(criteria).list();

        session.close();
        return  employees;
    }

    @Override
    public Employee create(Employee employee) {
        Session session=HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.persist(employee);
            session.getTransaction().commit();
        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }
        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        Session session=HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.merge(employee);
            session.getTransaction().commit();
        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return employee;
    }

    @Override
    public boolean deleteById(Long id) {
        Session session=HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            Employee employee=this.findById(id);
            session.remove(employee);
            session.getTransaction().commit();
        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }finally {
            session.close();
        }

        return true;
    }
}
