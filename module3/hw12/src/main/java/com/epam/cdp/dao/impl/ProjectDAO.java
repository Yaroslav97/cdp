package com.epam.cdp.dao.impl;

import com.epam.cdp.dao.CrudRepository;
import com.epam.cdp.model.Employee;
import com.epam.cdp.model.Project;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class ProjectDAO implements CrudRepository<Project, Long> {

    private SessionFactory sessionFactory = null;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public ProjectDAO() {
    }

    public ProjectDAO(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
    }

    @Override
    public Project save(Project project) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(project);
        session.getTransaction().commit();
        session.close();
        return project;
    }

    @Override
    public Project findOne(Long id) {
        Session session = sessionFactory.openSession();
        Project project = (Project) session.load(Project.class, id);
        session.close();
        return project;
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
    public void delete(Project project) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(project);
        session.getTransaction().commit();
        session.close();
    }

    public void assignEmployeeForProject(long employeeID, long projectID) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Employee employee = (Employee) session.load(Employee.class, employeeID);
        Project project = findOne(projectID);
        employee.getProjects().add(project);
        session.update(employee);

        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<Project> findAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Project> list = session.createCriteria(Project.class).list();
        session.close();
        return list;
    }

}
