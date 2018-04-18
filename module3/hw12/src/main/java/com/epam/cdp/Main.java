package com.epam.cdp;

import com.epam.cdp.dao.impl.EmployeeDAO;
import com.epam.cdp.dao.impl.ProjectDAO;
import com.epam.cdp.service.EmployeeService;
import com.epam.cdp.util.HibernateSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Main {

    public static void main(String[] args) {

        Session session = HibernateSession.getSessionFactory().openSession();
        session.beginTransaction();

       /* Developer employee = new Developer();
        employee.setFirstName("Yaroslav");
        employee.setLastName("Poliakov");
        employee.setEmail("yaroslav.poliakov@epam.com");
        employee.setStatus(EmployeeStatus.ACTIVE);
        employee.setAddress(new Address("Ukraine", "Kharkiv"));
        employee.setSkills("Java");

        Personal personal = new Personal("male", "0993048924");
        employee.setPersonal(personal);

        Project project = new Project("Ooyala");
        employee.setProjects(Collections.singletonList(project));

        //Unit unit = new Unit("Java", Collections.singletonList(employee));
        Unit unit = new Unit();
        unit.setName("Java");
        employee.setUnit(unit);

        System.out.println(employee);

        session.save(project);
        session.save(employee);
        session.save(unit);

        session.getTransaction().commit();
        HibernateSession.shutdown();*/


        SessionFactory sessionFactory = HibernateSession.getSessionFactory();
        EmployeeDAO employeeDAO = new EmployeeDAO(sessionFactory);
        ProjectDAO projectDAO = new ProjectDAO(sessionFactory);
        //System.out.println(employeeDAO.findByID((long) 1));
        //projectDAO.assignEmployeeForProject(4, 5);

        EmployeeService employeeService = new EmployeeService();
        System.out.println(employeeService.findByName("Yaroslav"));

    }
}
