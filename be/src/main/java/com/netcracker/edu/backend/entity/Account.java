package com.netcracker.edu.backend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "user_account", schema = "backend")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "account_id")
    private int accountId;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    @JsonManagedReference
    private StudentProfessor studentProfessor;

    public Account() {
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

    public StudentProfessor getStudentProfessor() {
        return studentProfessor;
    }

    public void setStudentProfessor(StudentProfessor studentProfessor) {
        this.studentProfessor = studentProfessor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return accountId == account.accountId &&
                Objects.equals(login, account.login) &&
                Objects.equals(password, account.password) &&
                Objects.equals(role, account.role) &&
                Objects.equals(studentProfessor, account.studentProfessor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, login, password, role, studentProfessor);
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", studentProfessor=" + studentProfessor +
                '}';
    }
}

