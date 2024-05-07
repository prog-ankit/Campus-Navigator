package com.springboot.university.requestresponse.request;

import com.springboot.university.annotation.SizeValidator;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;
@SizeValidator
public class UniversityRequestDto {
    private int universityId;
    @NotBlank(message = "University name must be provided.")
    @Size(min=2,message = "University name must be larger than 2")
    private String universityName;
    private int numberOfColleges;
    @Valid
    private List<CollegeRequestDto> listOfColleges;


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

    public List<CollegeRequestDto> getListOfColleges() {
        return listOfColleges;
    }

    public void setListOfColleges(List<CollegeRequestDto> listOfColleges) {
        this.listOfColleges = listOfColleges;
    }

    public int getUniversityId() {
        return universityId;
    }

    public void setUniversityId(int universityId) {
        this.universityId = universityId;
    }

    public UniversityRequestDto() {
    }

    public UniversityRequestDto(int universityId, String universityName, int numberOfColleges, List<CollegeRequestDto> listOfColleges) {
        this.universityId = universityId;
        this.universityName = universityName;
        this.numberOfColleges = numberOfColleges;
        this.listOfColleges = listOfColleges;
    }
}
