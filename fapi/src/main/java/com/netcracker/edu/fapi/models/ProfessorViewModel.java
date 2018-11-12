package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfessorViewModel {
    private int professorId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private Date birthday;
    private AccountViewModel account;

    public ProfessorViewModel() {
    }

    public ProfessorViewModel(int professorId, String firstName, String lastName, String email, String address, Date birthday, AccountViewModel account) {
        this.professorId = professorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.account = account;
    }

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
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

    public AccountViewModel getAccount() {
        return account;
    }

    public void setAccount(AccountViewModel account) {
        this.account = account;
    }
}
