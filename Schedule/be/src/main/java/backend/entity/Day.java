package backend.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "study_day", schema = "project")
public class Day {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "day_number")
    private int id;

    @Column(name = "day_name")
    private String dayName;

    public Day() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return id == day.id &&
                dayName.equals(day.dayName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dayName);
    }
}
