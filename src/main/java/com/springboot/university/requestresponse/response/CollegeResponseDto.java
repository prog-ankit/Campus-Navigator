package com.springboot.university.requestresponse.response;

public class CollegeResponseDto {
    private int collegeId;
    private String collegeName;

    public CollegeResponseDto() {
    }

    public CollegeResponseDto(int collegeId, String collegeName) {
        this.collegeId = collegeId;
        this.collegeName = collegeName;
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
}
