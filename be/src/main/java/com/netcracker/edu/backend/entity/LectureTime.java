package com.netcracker.edu.backend.entity;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name = "lecture_time", schema = "backend")
public class LectureTime {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "number")
    private int number;

    @Column(name = "begin")
    private Time begin;

    @Column(name = "end")
    private Time end;

    public LectureTime() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Time getBegin() {
        return begin;
    }

    public void setBegin(Time begin) {
        this.begin = begin;
    }

    public Time getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LectureTime that = (LectureTime) o;
        return id == that.id &&
                number == that.number &&
                Objects.equals(begin, that.begin) &&
                Objects.equals(end, that.end);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, begin, end);
    }
}
