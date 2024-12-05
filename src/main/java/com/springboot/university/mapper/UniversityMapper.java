package com.springboot.university.mapper;

import com.springboot.university.models.University;
import com.springboot.university.requestresponse.request.UniversityRequestDto;
import com.springboot.university.requestresponse.response.UniversityResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UniversityMapper {
    UniversityMapper UNIVERSITY_MAPPER = Mappers.getMapper(UniversityMapper.class);

    UniversityRequestDto universityToUniversityRequestDto(University university);
//    @Mapping(target = "universityId",ignore = true)
    University universityRequestDtotoUniversity(UniversityRequestDto universityRequestDto);
    UniversityResponseDto universityToUniversityResponseDto(University university);

}
