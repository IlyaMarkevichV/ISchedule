package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VisitViewModel {
    private int id;
    private ScheduleViewModel schedule;
    private StudentViewModel student;
    private boolean isArrived;

    public VisitViewModel() {
    }

    public VisitViewModel(int id, ScheduleViewModel schedule, StudentViewModel student, boolean isArrived) {
        this.id = id;
        this.schedule = schedule;
        this.student = student;
        this.isArrived = isArrived;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ScheduleViewModel getSchedule() {
        return schedule;
    }

    public void setSchedule(ScheduleViewModel schedule) {
        this.schedule = schedule;
    }

    public StudentViewModel getStudent() {
        return student;
    }

    public void setStudent(StudentViewModel student) {
        this.student = student;
    }

    public boolean isArrived() {
        return isArrived;
    }

    public void setArrived(boolean arrived) {
        isArrived = arrived;
    }
}
