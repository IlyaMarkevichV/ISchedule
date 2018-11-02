package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupSubjectViewModel {
    private int id;
    private GroupViewModel group;
    private SubjectViewModel subject;

    public GroupSubjectViewModel() {
    }

    public GroupSubjectViewModel(int id, GroupViewModel group, SubjectViewModel subject) {
        this.id = id;
        this.group = group;
        this.subject = subject;
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

    public SubjectViewModel getSubject() {
        return subject;
    }

    public void setSubject(SubjectViewModel subject) {
        this.subject = subject;
    }
}
