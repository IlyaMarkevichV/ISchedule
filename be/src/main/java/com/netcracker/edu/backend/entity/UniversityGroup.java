package com.netcracker.edu.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "university_group", schema = "project")
public class UniversityGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "group_id")
    private int groupId;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;

    @Column(name = "speciality")
    private String speciality;

    @Column(name = "grade")
    private int grade;

    @Column(name = "graduation")
    private Date graduation;

    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "groups")
    private Collection<Lesson> lessons;

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

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Date getGraduation() {
        return graduation;
    }

    public void setGraduation(Date graduation) {
        this.graduation = graduation;
    }

    public Collection<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(Collection<Lesson> lessons) {
        this.lessons = lessons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UniversityGroup that = (UniversityGroup) o;
        return groupId == that.groupId &&
                grade == that.grade &&
                Objects.equals(faculty, that.faculty) &&
                Objects.equals(speciality, that.speciality) &&
                Objects.equals(graduation, that.graduation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId, faculty, speciality, grade, graduation);
    }

    static UniversityGroup cloneGroup(UniversityGroup group) {
        UniversityGroup clonedGroup = new UniversityGroup();
        clonedGroup.groupId = group.groupId;
        clonedGroup.faculty = group.faculty;
        clonedGroup.speciality = group.speciality;
        clonedGroup.grade = group.grade;
        clonedGroup.graduation = group.graduation;
        return clonedGroup;
    }
}
