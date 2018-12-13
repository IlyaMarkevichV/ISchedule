package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AttendanceViewModel {
    private int id;
    private StudentViewModel student;
    private LessonViewModel lesson;
    private Date date;
    private byte status;

    public AttendanceViewModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StudentViewModel getStudent() {
        return student;
    }

    public void setStudent(StudentViewModel student) {
        this.student = student;
    }

    public LessonViewModel getLesson() {
        return lesson;
    }

    public void setLesson(LessonViewModel lesson) {
        this.lesson = lesson;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }
}
