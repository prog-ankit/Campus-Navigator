package com.springboot.university.service.serviceimpl;

import com.springboot.university.mapper.CollegeMapper;
import com.springboot.university.mapper.UniversityMapper;
import com.springboot.university.models.College;
import com.springboot.university.models.University;
import com.springboot.university.repository.CollegeRepository;
import com.springboot.university.repository.UniversityRepository;
import com.springboot.university.requestresponse.request.CollegeRequestDto;
import com.springboot.university.requestresponse.request.UniversityRequestDto;
import com.springboot.university.requestresponse.response.UniversityResponseDto;
import com.springboot.university.service.UniversityService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UniversityServicesImpl implements UniversityService {
    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    CollegeRepository collegeRepository;
    static final Logger logs = LoggerFactory.getLogger(UniversityServicesImpl.class);

    @Transactional
    @Override
    public UniversityResponseDto addUniversity(UniversityRequestDto universityRequestDto) {
        University storeUniversity = UniversityMapper.UNIVERSITY_MAPPER.universityRequestDtotoUniversity(universityRequestDto);
        List<College> colleges = universityRequestDto.getListOfColleges().stream()
                .map(collegeRequestDto -> {
                    College c = CollegeMapper.COLLEGE_MAPPER.collegeRequestDtotoCollege(collegeRequestDto);
                    c.setUniversity(storeUniversity);
                    return c;
                })
                .collect(Collectors.toList());
        storeUniversity.setListOfColleges(colleges);
        University savedUniversity = universityRepository.save(storeUniversity);
        if(savedUniversity.getUniversityId() >0) {
            logs.info("A New University is Created, Admissions Open..");
            return UniversityMapper.UNIVERSITY_MAPPER.universityToUniversityResponseDto(savedUniversity);
        } else {
            logs.error("Something went wrong..");
            return null;
        }

    }

    @Override
    public List<UniversityResponseDto> getAllUniversities() {
        List<University> allUniversities = universityRepository.findAll();
        List<UniversityResponseDto> universityResponseDtos = new ArrayList<>();
        for(University u : allUniversities) {
            universityResponseDtos.add(UniversityMapper.UNIVERSITY_MAPPER.universityToUniversityResponseDto(u));
        }
        return universityResponseDtos;
    }

    @Override
    public UniversityResponseDto getUniversity(int universityId) {
        Optional<University> universityExist = universityRepository.findById(universityId);
        if(universityExist.isPresent()) {
            University university = universityExist.get();
            return UniversityMapper.UNIVERSITY_MAPPER.universityToUniversityResponseDto(university);
        }
        return null;
    }

    @Transactional
    @Override
    public UniversityResponseDto updateUniversity(int universityId, UniversityRequestDto universityRequestDto) {
        Optional<University> existUniversity = universityRepository.findById(universityId);
        if(existUniversity.isPresent()) {
            University university = UniversityMapper.UNIVERSITY_MAPPER.universityRequestDtotoUniversity(universityRequestDto);
            university.setUniversityId(universityId);

            Set<Integer> collegeIds = existUniversity.get().getListOfColleges().stream()
                    .map(College::getCollegeId).
                    collect(Collectors.toSet());

            List<College> colleges = new ArrayList<>();
            for(CollegeRequestDto collegeRequestDto : universityRequestDto.getListOfColleges()) {
                collegeIds.remove(collegeRequestDto.getCollegeId());
                College college;
                if(collegeRequestDto.getCollegeId()>0) {
                    college = collegeRepository.findById(collegeRequestDto.getCollegeId())
                            .orElseThrow(() -> new EntityNotFoundException("College not found with ID: " + collegeRequestDto.getCollegeId()));
                    college.setCollegeName(collegeRequestDto.getCollegeName());
                } else {
                    college = CollegeMapper.COLLEGE_MAPPER.collegeRequestDtotoCollege(collegeRequestDto);
                }
                college.setUniversity(university);
                colleges.add(college);
            }

            if(!collegeIds.isEmpty()) {
                collegeRepository.deleteAllById(collegeIds);
            }
            university.setListOfColleges(colleges);
            University savedUniversity = universityRepository.save(university);
            logs.info("Colleges are updated!!!");
            return UniversityMapper.UNIVERSITY_MAPPER.universityToUniversityResponseDto(savedUniversity);
        }
        return null;
    }

    @Override
    public List<UniversityResponseDto> udpateBulkUniversity(List<UniversityRequestDto> universityRequestDtos) {
        Set<Integer> universityIds = universityRepository.findAll().stream()
                .map(University::getUniversityId)
                .collect(Collectors.toSet());;
        for(UniversityRequestDto universityRequestDto : universityRequestDtos) {
            if(universityRequestDto.getUniversityId() > 0 ){
                updateUniversity(universityRequestDto.getUniversityId(),universityRequestDto);
            } else {
                addUniversity(universityRequestDto);
            }
            universityIds.remove(universityRequestDto.getUniversityId());
        }
        if(!universityIds.isEmpty()) {
            universityRepository.deleteAllById(universityIds);
        }
        return universityRepository.findAll().stream().map(UniversityMapper.UNIVERSITY_MAPPER::universityToUniversityResponseDto).collect(Collectors.toList());
    }

    @Override
    public boolean deleteUniversity(int universityId) {
        Optional<University> universityExist = universityRepository.findById(universityId);
        if(universityExist.isPresent()) {
            universityRepository.delete(universityExist.get());
            return true;
        }
        return false;
    }
}
