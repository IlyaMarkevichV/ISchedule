package com.netcracker.edu.backend.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "university_group", schema = "backend")
public class UniversityGroup {
    @Id
    @Column(name = "group_id")
    private int groupId;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @Column(name = "grade")
    private int grade;

    @Column(name = "until")
    private Date date;

    public UniversityGroup() {
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
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
        UniversityGroup that = (UniversityGroup) o;
        return groupId == that.groupId &&
                grade == that.grade &&
                Objects.equals(faculty, that.faculty) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, faculty, grade, date);
    }

    @Override
    public String toString() {
        return "UniversityGroup{" +
                "groupId=" + groupId +
                ", faculty=" + faculty +
                ", grade=" + grade +
                ", date=" + date +
                '}';
    }

    static UniversityGroup cloneGroup(UniversityGroup group) {
        UniversityGroup cloned = new UniversityGroup();
        cloned.groupId = group.groupId;
        cloned.grade = group.grade;
        cloned.date = group.date;
        cloned.faculty = group.faculty;
        return cloned;
    }
}
