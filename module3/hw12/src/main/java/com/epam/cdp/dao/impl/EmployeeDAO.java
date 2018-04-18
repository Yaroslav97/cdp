package com.epam.cdp.dao.impl;

import com.epam.cdp.dao.CrudRepository;
import com.epam.cdp.model.Developer;
import com.epam.cdp.model.Employee;
import com.epam.cdp.model.QA;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.Iterator;
import java.util.List;

public class EmployeeDAO implements CrudRepository<Employee, Long> {

    private SessionFactory sessionFactory = null;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public EmployeeDAO() {
    }

    public EmployeeDAO(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    public Employee save(Employee employee) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(employee);
        session.getTransaction().commit();
        session.close();
        return employee;
    }

    @Override
    public Employee findOne(Long id) {
        Session session = sessionFactory.openSession();
        Employee employee = (Employee) session.load(Employee.class, id);
        session.close();
        return employee;
    }

    public Employee findByID(Long employeeID) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Employee where employee_id = :employeeID");
        query.setParameter("employeeID", employeeID);
        query.setCacheable(true);

        Employee employee = null;

        Iterator it = query.list().iterator();
        while (it.hasNext()) {
            employee = (Employee) it.next();
        }
        transaction.commit();
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
    public void delete(Employee employee) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(employee);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Employee> findAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Employee> list = session.createCriteria(Employee.class).list();
        session.close();
        return list;
    }

    public List<Developer> findAllDev() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Developer> list = session.createCriteria(Developer.class).list();
        session.close();
        return list;
    }

    public List<QA> findAllQA() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<QA> list = session.createCriteria(QA.class).list();
        session.close();
        return list;
    }

}
