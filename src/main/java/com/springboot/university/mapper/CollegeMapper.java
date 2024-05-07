package com.springboot.university.mapper;

import com.springboot.university.models.College;
import com.springboot.university.requestresponse.request.CollegeRequestDto;
import com.springboot.university.requestresponse.response.CollegeResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring")
public interface CollegeMapper {
    CollegeMapper COLLEGE_MAPPER = Mappers.getMapper(CollegeMapper.class);

    @Mapping(target="collegeId",ignore = true)
    College collegeRequestDtotoCollege(CollegeRequestDto collegeRequestDto);

    List<College> collegeRequestDtostoCollegeList(List<CollegeRequestDto> collegeRequestDtos);
    CollegeRequestDto collegeToCollegeRequestDto(College college);
    CollegeResponseDto collegeToCollegeResponseDto(College college);
}
