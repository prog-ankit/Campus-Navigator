package com.springboot.university.service;

import com.springboot.university.requestresponse.request.UniversityRequestDto;
import com.springboot.university.requestresponse.response.UniversityResponseDto;

import java.util.List;

public interface UniversityService {
    UniversityResponseDto addUniversity(UniversityRequestDto universityRequestDto);
    List<UniversityResponseDto> getAllUniversities();
    UniversityResponseDto getUniversity(int universityId);
    UniversityResponseDto updateUniversity(int universityId, UniversityRequestDto universityRequestDto);
    List<UniversityResponseDto> udpateBulkUniversity(List<UniversityRequestDto> universityRequestDtos);
    boolean deleteUniversity(int universityId);
}
