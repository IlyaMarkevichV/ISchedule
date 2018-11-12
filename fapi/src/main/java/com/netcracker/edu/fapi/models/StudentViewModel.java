package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentViewModel {
    private int studentId;
    private GroupViewModel group;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String email;
    private String address;
    private AccountViewModel account;

    public StudentViewModel() {
    }

    public StudentViewModel(int studentId, GroupViewModel group, String firstName,
                            String lastName, Date birthday, String email, String address,
                            AccountViewModel account) {
        this.studentId = studentId;
        this.group = group;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.email = email;
        this.address = address;
        this.account = account;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public GroupViewModel getGroup() {
        return group;
    }

    public void setGroup(GroupViewModel group) {
        this.group = group;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
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

    public AccountViewModel getAccount() {
        return account;
    }

    public void setAccount(AccountViewModel account) {
        this.account = account;
    }
}
