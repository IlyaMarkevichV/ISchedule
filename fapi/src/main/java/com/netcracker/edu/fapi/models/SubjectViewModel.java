package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubjectViewModel {
    private int id;
    private String subjectName;
    private ProfessorViewModel professor;

    public SubjectViewModel() {
    }

    public SubjectViewModel(int id, String subjectName, ProfessorViewModel professor) {
        this.id = id;
        this.subjectName = subjectName;
        this.professor = professor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public ProfessorViewModel getProfessor() {
        return professor;
    }

    public void setProfessor(ProfessorViewModel professor) {
        this.professor = professor;
    }
}
