package com.improvingskills.dao;

import com.improvingskills.entities.Car;
import com.improvingskills.entities.Employee;
import com.improvingskills.util.HibernateUtil;
import jakarta.persistence.PersistenceException;
import org.hibernate.Session;

public class CarDAOImpl implements  CarDAO{
    @Override
    public Car findById(Long id) {
        Session session=HibernateUtil.getSessionFactory().openSession();
        Car car=session.find(Car.class, id);
        session.close();
        return car;
    }

    @Override
    public Car create(Car car) {
        Session session= HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.persist(car);
            session.getTransaction().commit();
        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return car;
    }

    @Override
    public Boolean deleteById(Long id) {
        Session session=HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            Car car=this.findById(id);
            session.remove(car);
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
