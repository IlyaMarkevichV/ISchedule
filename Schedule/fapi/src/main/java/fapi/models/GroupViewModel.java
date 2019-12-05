package fapi.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupViewModel {
    private String id;
    private FacultyViewModel faculty;
    private String speciality;
    private int grade;
    private Date graduation;

    public GroupViewModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FacultyViewModel getFaculty() {
        return faculty;
    }

    public void setFaculty(FacultyViewModel faculty) {
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
}
