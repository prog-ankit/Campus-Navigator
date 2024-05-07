package com.springboot.university.requestresponse.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CollegeRequestDto {
    private int collegeId;
    @NotBlank(message = "College Name must be provided.")
    @Size(min=4,message = "College Name must be larger than 4")
    private String collegeName;

    public CollegeRequestDto() {
    }

    public CollegeRequestDto(int collegeId, String collegeName) {
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
