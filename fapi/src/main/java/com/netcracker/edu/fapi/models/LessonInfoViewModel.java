package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LessonInfoViewModel {
    private int lessonInfoId;
    private String lessonName;

    public LessonInfoViewModel() {
    }

    public int getLessonInfoId() {
        return lessonInfoId;
    }

    public void setLessonInfoId(int lessonInfoId) {
        this.lessonInfoId = lessonInfoId;
    }

    public String getLessonName() {
        return lessonName;
    }

    public void setLessonName(String lessonName) {
        this.lessonName = lessonName;
    }
}
