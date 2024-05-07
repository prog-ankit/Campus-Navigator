package com.springboot.university.service;


import com.springboot.university.requestresponse.request.CollegeRequestDto;
import com.springboot.university.requestresponse.response.CollegeResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CollegeService {
    CollegeResponseDto addCollege(int universityId, CollegeRequestDto collegeRequestDTO);
    boolean deleteCollege(int university_id, int collegeId);
    List<CollegeResponseDto> bulkUpdate(int universityId, List<CollegeRequestDto> universityDTO);
    CollegeResponseDto singleUpdate(int universityId, CollegeRequestDto collegeDTO);
    List<CollegeResponseDto> getColleges(int universityId);
}
