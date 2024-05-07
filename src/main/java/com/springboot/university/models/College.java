package com.springboot.university.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "College")
public class College {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native")
    private int collegeId;
    private String collegeName;
    @ManyToOne
    @JoinColumn(name = "university_id",referencedColumnName = "universityId",nullable = false)
    private University university;

    public College() {
    }

    public College(int collegeId, String collegeName, University university) {
        this.collegeId = collegeId;
        this.collegeName = collegeName;
        this.university = university;
    }

    public int getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(int collegeId) {
        this.collegeId = collegeId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }

    @Override
    public String toString() {
        return "College{" +
                "collegeId=" + collegeId +
                ", collegeName='" + collegeName + '\'' +
                ", university=" + university +
                '}';
    }
}
