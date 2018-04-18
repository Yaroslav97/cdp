package com.epam.cdp.dao.impl;

import com.epam.cdp.dao.CrudRepository;
import com.epam.cdp.model.Employee;
import com.epam.cdp.model.Unit;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UnitDAO implements CrudRepository<Unit, Long> {

    private SessionFactory sessionFactory = null;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public UnitDAO() {
    }

    public UnitDAO(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    public Unit save(Unit unit) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(unit);
        session.getTransaction().commit();
        session.close();
        return unit;
    }

    @Override
    public Unit findOne(Long id) {
        Session session = sessionFactory.openSession();
        Unit employee = (Unit) session.load(Unit.class, id);
        session.close();
        return employee;
    }

    @Override
    public void delete(Long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(id);
        session.getTransaction().commit();
        session.close();

    }

    @Override
    public void delete(Unit unit) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(unit);
        session.getTransaction().commit();
        session.close();
    }

    public void addEmployeeToUnit(long unitID, long employeeID) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("update Employee set unit_id = :unitID" +
                " where id = :employeeID");
        query.setParameter("unitID", unitID);
        query.setParameter("employeeID", employeeID);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Unit> findAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        return session.createCriteria(Unit.class).list();
    }

}
