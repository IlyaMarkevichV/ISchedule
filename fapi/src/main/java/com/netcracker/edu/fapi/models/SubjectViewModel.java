package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubjectViewModel {
    private int subjectId;
    private String subjectName;
    private ProfessorViewModel professor;

    public SubjectViewModel() {
    }

    public SubjectViewModel(int subjectId, String subjectName, ProfessorViewModel professor) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.professor = professor;
    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
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
