package com.netcracker.edu.backend.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "professor", schema = "backend")
public class Professor {
    @Id
    @Column(name = "professor_id")
    private int professorId;

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }
    private String firstName;

    @Basic
    @Column(name = "last_name")
    private String lastName;

    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "address")
    private String address;

    @Basic
    @Column(name = "birthday")
    private Date birthday;


    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Professor professor = (Professor) o;
        return professorId == professor.professorId &&
                Objects.equals(firstName, professor.firstName) &&
                Objects.equals(lastName, professor.lastName) &&
                Objects.equals(email, professor.email) &&
                Objects.equals(address, professor.address) &&
                Objects.equals(birthday, professor.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(professorId, firstName, lastName, email, address, birthday);
    }
}
