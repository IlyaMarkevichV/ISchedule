package com.netcracker.edu.backend.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "faculty", schema = "backend")
public class Faculty {
    @Id
    @Column(name = "faculty_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int facultyId;

    @Column(name = "faculty_name")
    private String facultyName;

    @OneToMany(cascade = CascadeType.ALL)

    private Set<UniversityGroup> universityGroup = new HashSet<UniversityGroup>();

    public int getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(int facultyId) {
        this.facultyId = facultyId;
    }

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public Set<UniversityGroup> getUniversityGroup() {
        return universityGroup;
    }

    public void setUniversityGroup(Set<UniversityGroup> universityGroup) {
        this.universityGroup = universityGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty that = (Faculty) o;
        return facultyId == that.facultyId &&
                Objects.equals(facultyName, that.facultyName) &&
                Objects.equals(universityGroup, that.universityGroup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(facultyId, facultyName, universityGroup);
    }
}
