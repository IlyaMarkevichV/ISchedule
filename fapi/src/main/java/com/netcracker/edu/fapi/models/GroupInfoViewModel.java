package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupInfoViewModel {
    private int id;
    private GroupViewModel group;
    private ProfessorViewModel professor;
    private String message;
    private Date message_date;

    public GroupInfoViewModel() {
    }

    public GroupInfoViewModel(int id, GroupViewModel group, ProfessorViewModel professor, String message, Date message_date) {
        this.id = id;
        this.group = group;
        this.professor = professor;
        this.message = message;
        this.message_date = message_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public GroupViewModel getGroup() {
        return group;
    }

    public void setGroup(GroupViewModel group) {
        this.group = group;
    }

    public ProfessorViewModel getProfessor() {
        return professor;
    }

    public void setProfessor(ProfessorViewModel professor) {
        this.professor = professor;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getMessage_date() {
        return message_date;
    }

    public void setMessage_date(Date message_date) {
        this.message_date = message_date;
    }
}
