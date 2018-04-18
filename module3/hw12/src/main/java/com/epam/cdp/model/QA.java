package com.epam.cdp.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.util.List;

@Entity()
@Table(name = "qa")
public class QA extends Employee {

    @Enumerated(EnumType.STRING)
    private QASkill skill;

    public QA() {
    }

    public QA(Long id, String firstName, String lastName, String email, EmployeeStatus status, Address address, Personal personal, List<Project> projects, Unit unit, QASkill skill) {
        super(id, firstName, lastName, email, status, address, personal, projects, unit);
        this.skill = skill;
    }

    public QASkill getSkill() {
        return skill;
    }

    public void setSkill(QASkill skill) {
        this.skill = skill;
    }

}
