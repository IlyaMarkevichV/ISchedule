package com.netcracker.edu.backend.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "lesson_date", schema = "project")
public class LessonDate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "lesson_id")
    private Lesson lesson;

    @Column(name = "lesson_date")
    private Date date;

    public LessonDate() {
    }

    public LessonDate(Lesson lesson, Date date) {
        this.lesson = lesson;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LessonDate that = (LessonDate) o;
        return id == that.id &&
                lesson.equals(that.lesson) &&
                date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lesson, date);
    }
}
