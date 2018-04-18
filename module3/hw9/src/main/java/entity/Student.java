package entity;

import java.sql.Timestamp;

public class Student {

    private long id;
    private String firstName;
    private String lastName;
    private Timestamp birsday;
    private String phone;
    private String skill;
    private Timestamp createdDatetime;
    private Timestamp updatedDatetime;

    public Student(long id, String firstName, String lastName, Timestamp birsday, String phone, String skill, Timestamp createdDatetime, Timestamp updatedDatetime) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birsday = birsday;
        this.phone = phone;
        this.skill = skill;
        this.createdDatetime = createdDatetime;
        this.updatedDatetime = updatedDatetime;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Timestamp getBirsday() {
        return birsday;
    }

    public void setBirsday(Timestamp birsday) {
        this.birsday = birsday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public Timestamp getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(Timestamp createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public Timestamp getUpdatedDatetime() {
        return updatedDatetime;
    }

    public void setUpdatedDatetime(Timestamp updatedDatetime) {
        this.updatedDatetime = updatedDatetime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Student{");
        sb.append("id=").append(id);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", birsday=").append(birsday);
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", skill='").append(skill).append('\'');
        sb.append(", createdDatetime=").append(createdDatetime);
        sb.append(", updatedDatetime=").append(updatedDatetime);
        sb.append('}');
        return sb.toString();
    }
}
