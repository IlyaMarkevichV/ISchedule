package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ScheduleViewModel {
    private int scheduleId;
    private SubjectViewModel subject;
    private Date date;
    private LectureTimeViewModel time;

    public ScheduleViewModel() {
    }

    public ScheduleViewModel(int scheduleId, SubjectViewModel subject, Date date, LectureTimeViewModel time) {
        this.scheduleId = scheduleId;
        this.subject = subject;
        this.date = date;
        this.time = time;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public SubjectViewModel getSubject() {
        return subject;
    }

    public void setSubject(SubjectViewModel subject) {
        this.subject = subject;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LectureTimeViewModel getTime() {
        return time;
    }

    public void setTime(LectureTimeViewModel time) {
        this.time = time;
    }
}
