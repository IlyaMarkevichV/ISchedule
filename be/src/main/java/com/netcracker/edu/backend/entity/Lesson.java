package com.netcracker.edu.backend.entity;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "lesson", schema = "project")
public class Lesson {
    @Id
    @GeneratedValue
    @Column(name = "lesson_id")
    private int lessonId;

    @ManyToOne
    @JoinColumn(name = "lesson_info_id")
    private LessonInfo lessonInfo;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "lesson_time_id")
    private LessonTime lessonTime;

    @Column(name = "lesson_room")
    private int lessonRoom;

    @ManyToOne
    @JoinColumn(name = "study_day")
    private Day day;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "lesson",
            joinColumns = {@JoinColumn(name = "lesson_id")},
            inverseJoinColumns = {@JoinColumn(name = "group_id")})
    private Collection<UniversityGroup> groups;

    public Lesson() {
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public LessonInfo getLessonInfo() {
        return lessonInfo;
    }

    public void setLessonInfo(LessonInfo lessonInfo) {
        this.lessonInfo = lessonInfo;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public LessonTime getLessonTime() {
        return lessonTime;
    }

    public void setLessonTime(LessonTime lessonTime) {
        this.lessonTime = lessonTime;
    }

    public int getLessonRoom() {
        return lessonRoom;
    }

    public void setLessonRoom(int lessonRoom) {
        this.lessonRoom = lessonRoom;
    }

    public Day getDay() {
        return day;
    }

    public void setDay(Day day) {
        this.day = day;
    }

    public Collection<UniversityGroup> getGroups() {
        return groups;
    }

    public void setGroups(Collection<UniversityGroup> groups) {
        this.groups = groups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lesson lesson = (Lesson) o;
        return lessonId == lesson.lessonId &&
                lessonRoom == lesson.lessonRoom &&
                lessonInfo.equals(lesson.lessonInfo) &&
                professor.equals(lesson.professor) &&
                lessonTime.equals(lesson.lessonTime) &&
                day.equals(lesson.day) &&
                groups.equals(lesson.groups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lessonId, lessonInfo, professor, lessonTime, lessonRoom, day, groups);
    }
}
