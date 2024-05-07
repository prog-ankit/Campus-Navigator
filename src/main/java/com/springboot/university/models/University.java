package com.springboot.university.models;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;

@Entity
@Table(name = "University")
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "native")
    private int universityId;
    private String universityName;
    private int numberOfColleges;
    @OneToMany(mappedBy = "university",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<College> listOfColleges;

    public University() {
    }

    public University(int universityId, String universityName, int numberOfColleges, List<College> listOfColleges) {
        this.universityId = universityId;
        this.universityName = universityName;
        this.numberOfColleges = numberOfColleges;
        this.listOfColleges = listOfColleges;
    }

    public int getUniversityId() {
        return universityId;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public int getNumberOfColleges() {
        return numberOfColleges;
    }

    public void setNumberOfColleges(int numberOfColleges) {
        this.numberOfColleges = numberOfColleges;
    }

    public List<College> getListOfColleges() {
        return listOfColleges;
    }

    public void setListOfColleges(List<College> listOfColleges) {
        this.listOfColleges = listOfColleges;
    }

    @Override
    public String toString() {
        return "University{" +
                "universityId=" + universityId +
                ", universityName='" + universityName + '\'' +
                ", numberOfColleges=" + numberOfColleges +
                ", listOfColleges=" + listOfColleges +
                '}';
    }
}
