package com.netcracker.edu.backend.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "student_group", schema = "backend")
public class StudentGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private UniversityGroup group;

    public StudentGroup() {
    }

    /*put all needed data from Account to StudentGroup to make true POST request*/
    public StudentGroup(Account account) {
        this.id = account.getStudentGroupId();
        this.account = account;
        this.group = account.getStudentProfessor().getGroup();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public UniversityGroup getGroup() {
        return group;
    }

    public void setGroup(UniversityGroup group) {
        this.group = group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentGroup that = (StudentGroup) o;
        return id == that.id &&
                Objects.equals(account, that.account) &&
                Objects.equals(group, that.group);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, account, group);
    }

    @Override
    public String toString() {
        return "StudentGroup{" +
                "id=" + id +
                ", account=" + account +
                ", group=" + group +
                '}';
    }
}
