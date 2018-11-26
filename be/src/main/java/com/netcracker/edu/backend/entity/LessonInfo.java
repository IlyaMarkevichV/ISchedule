package com.netcracker.edu.backend.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "lesson_info", schema = "project")
public class LessonInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "lesson_info_id")
    private int lessonInfoId;

    @Column(name = "lesson_name")
    private String lessonName;

    public LessonInfo() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonInfo lessonInfo = (LessonInfo) o;
        return lessonInfoId == lessonInfo.lessonInfoId &&
                lessonName.equals(lessonInfo.lessonName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lessonInfoId, lessonName);
    }
}
