package com.improvingskills.dao;

import com.improvingskills.entities.Direction;
import com.improvingskills.entities.Employee;
import com.improvingskills.util.HibernateUtil;
import jakarta.persistence.PersistenceException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class DirectionDAOImpl implements DirectionDAO{
    @Override
    public List<Direction> findAll() {
        Session session= HibernateUtil.getSessionFactory().openSession();

        //Consulta HQL
        Query<Direction> query=session.createQuery("from Direction", Direction.class);
        List<Direction> directions=query.list();

        /*Equivalente
        List<Direction> directions=session.createQuery("from Direction", Direction.class).list();*/

        session.close();
        return directions;
    }

    @Override
    public Direction findById(Long id) {
        Session session=HibernateUtil.getSessionFactory().openSession();
        Direction direction=session.find(Direction.class, id);
        return direction;
    }

    @Override
    public Direction create(Direction direction) {
        Session session=HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.persist(direction);
            session.getTransaction().commit();
        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        session.close();
        return direction;
    }

    @Override
    public Direction update(Direction direction) {
        Session session=HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.merge(direction);
            session.getTransaction().commit();
        }catch (PersistenceException e){
            e.printStackTrace();
            session.getTransaction().rollback();
        }finally {
            session.close();
        }

        return direction;
    }

    @Override
    public boolean deleteById(Long id) {
        Session session=HibernateUtil.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            Direction direction=this.findById(id);
            session.remove(direction);
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
