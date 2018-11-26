package com.netcracker.edu.backend.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "study_day", schema = "project")
public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "day_number")
    private int dayNumber;

    @Column(name = "day_name")
    private String dayName;

    public Day() {
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    public String getDayName() {
        return dayName;
    }

    public void setDayName(String dayName) {
        this.dayName = dayName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Day day = (Day) o;
        return dayNumber == day.dayNumber &&
                dayName.equals(day.dayName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayNumber, dayName);
    }
}
