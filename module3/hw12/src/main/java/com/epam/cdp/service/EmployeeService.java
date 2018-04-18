package com.epam.cdp.service;

import com.epam.cdp.model.Employee;
import com.epam.cdp.model.Project;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class EmployeeService {

    @PersistenceContext(unitName = "QueriesUnit")
    EntityManager entityManager;

    public List<Employee> findByName(String name) {
        CriteriaBuilder b = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = b.createQuery(Employee.class);
        Root<Employee> employee = criteriaQuery.from(Employee.class);
        criteriaQuery.select(employee).where(b.equal(employee.get("first_name"), name));

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
