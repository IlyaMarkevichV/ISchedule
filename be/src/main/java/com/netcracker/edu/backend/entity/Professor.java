package com.netcracker.edu.backend.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "professor", schema = "project")
public class Professor {
    @Id
    @Column(name = "professor_id")
    private int professorId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "academic_rank")
    private String academicRank;

    @Column(name = "field_of_research")
    private String fieldOfResearch;

    public Professor() {
    }

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getAcademicRank() {
        return academicRank;
    }

    public void setAcademicRank(String academicRank) {
        this.academicRank = academicRank;
    }

    public String getFieldOfResearch() {
        return fieldOfResearch;
    }

    public void setFieldOfResearch(String fieldOfResearch) {
        this.fieldOfResearch = fieldOfResearch;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Professor professor = (Professor) o;
        return professorId == professor.professorId &&
                Objects.equals(account, professor.account) &&
                Objects.equals(academicRank, professor.academicRank) &&
                Objects.equals(fieldOfResearch, professor.fieldOfResearch);
    }

    @Override
    public int hashCode() {
        return Objects.hash(professorId, account, academicRank, fieldOfResearch);
    }
}
