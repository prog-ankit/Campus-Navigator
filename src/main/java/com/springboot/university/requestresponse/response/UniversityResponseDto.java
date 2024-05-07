package com.springboot.university.requestresponse.response;

import java.util.List;

public class UniversityResponseDto {
    private int universityId;
    private String universityName;

    private int numberOfColleges;
    private List<CollegeResponseDto> listOfColleges;

    public UniversityResponseDto() {
    }

    public UniversityResponseDto(int universityId, String universityName, int numberOfColleges, List<CollegeResponseDto> listOfColleges) {
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

    public List<CollegeResponseDto> getListOfColleges() {
        return listOfColleges;
    }

    public void setListOfColleges(List<CollegeResponseDto> listOfColleges) {
        this.listOfColleges = listOfColleges;
    }
}
