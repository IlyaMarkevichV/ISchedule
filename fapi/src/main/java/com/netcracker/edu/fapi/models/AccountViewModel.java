package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountViewModel {
    private int accountId;
    private String login;
    private String password;
    private String role;

    @JsonManagedReference
    private StudentProfessorViewModel studentProfessor;

    public AccountViewModel() {
    }

    public AccountViewModel(int accountId, String email, String password, String role, StudentProfessorViewModel studentProfessor) {
        this.accountId = accountId;
        this.login = email;
        this.password = password;
        this.role = role;
        this.studentProfessor = studentProfessor;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public StudentProfessorViewModel getStudentProfessor() {
        return studentProfessor;
    }

    public void setStudentProfessor(StudentProfessorViewModel studentProfessor) {
        this.studentProfessor = studentProfessor;
    }
}
