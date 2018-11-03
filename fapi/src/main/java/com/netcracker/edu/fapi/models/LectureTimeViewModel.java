package com.netcracker.edu.fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Time;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LectureTimeViewModel {
    private int id;
    private int number;
    private Time begin;
    private Time end;

    public LectureTimeViewModel() {
    }

    public LectureTimeViewModel(int id, int number, Time begin, Time end) {
        this.id = id;
        this.number = number;
        this.begin = begin;
        this.end = end;
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
}
