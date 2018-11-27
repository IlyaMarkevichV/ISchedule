package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Date;
import java.util.Collection;
import java.util.Collections;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LessonViewModel {
    private int lessonId;
    private LessonInfoViewModel lessonInfo;
    private ProfessorViewModel professor;
    private LessonTimeViewModel lessonTime;
    private int lessonRoom;
    private DayViewModel day;
    private Collection<GroupViewModel> groups;

    public LessonViewModel() {
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public LessonInfoViewModel getLessonInfo() {
        return lessonInfo;
    }

    public void setLessonInfo(LessonInfoViewModel lessonInfo) {
        this.lessonInfo = lessonInfo;
    }

    public ProfessorViewModel getProfessor() {
        return professor;
    }

    public void setProfessor(ProfessorViewModel professor) {
        this.professor = professor;
    }

    public LessonTimeViewModel getLessonTime() {
        return lessonTime;
    }

    public void setLessonTime(LessonTimeViewModel lessonTime) {
        this.lessonTime = lessonTime;
    }

    public int getLessonRoom() {
        return lessonRoom;
    }

    public void setLessonRoom(int lessonRoom) {
        this.lessonRoom = lessonRoom;
    }

    public DayViewModel getDay() {
        return day;
    }

    public void setDay(DayViewModel day) {
        this.day = day;
    }

    public Collection<GroupViewModel> getGroups() {
        return groups;
    }

    public void setGroups(Collection<GroupViewModel> groups) {
        this.groups = groups;
    }
}
