package com.epam.cdp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity()
@Table(name = "developer")
public class Developer extends Employee<Developer> {

    @Column
    private String skills;

    public Developer() {
    }

    public Developer(String skills) {
        this.skills = skills;
    }

    public Developer(Long id, String firstName, String lastName, String email,
                     EmployeeStatus status, Address address, Personal personal,
                     List<Project> projects, Unit unit, String skills) {
        super(id, firstName, lastName, email, status, address, personal, projects, unit);
        this.skills = skills;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

}
