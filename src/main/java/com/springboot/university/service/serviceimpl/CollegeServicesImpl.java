package com.springboot.university.service.serviceimpl;


import com.springboot.university.mapper.CollegeMapper;
import com.springboot.university.models.College;
import com.springboot.university.models.University;
import com.springboot.university.repository.CollegeRepository;
import com.springboot.university.repository.UniversityRepository;
import com.springboot.university.requestresponse.request.CollegeRequestDto;
import com.springboot.university.requestresponse.response.CollegeResponseDto;
import com.springboot.university.service.CollegeService;
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
public class CollegeServicesImpl implements CollegeService {
    @Autowired
    UniversityRepository universityRepository;
    @Autowired
    CollegeRepository collegeRepository;
    static final Logger logs = LoggerFactory.getLogger(UniversityServicesImpl.class);

    @Override
    public CollegeResponseDto addCollege(int universityId, CollegeRequestDto collegeRequestDTO) {
        Optional<University> optionalUniveristy = universityRepository.findById(universityId);
        try {
            if(optionalUniveristy.isPresent()) {
                College dtoCollege = CollegeMapper.COLLEGE_MAPPER.collegeRequestDtotoCollege(collegeRequestDTO);
                dtoCollege.setUniversity(optionalUniveristy.get());
                College savedCollege = collegeRepository.save(dtoCollege);
                if(savedCollege.getCollegeId() > 0) {
                    logs.info("College is Developed!!");
                }
                return CollegeMapper.COLLEGE_MAPPER.collegeToCollegeResponseDto(savedCollege);
            } else {
                return null;
            }
        }catch(Exception e) {
            logs.error(e.toString());
        }

        return null;
    }

    @Override
    public boolean deleteCollege(int university_id, int collegeId) {
        try {
            University university = universityRepository.findById(university_id)
                    .orElseThrow(() -> new EntityNotFoundException("University not found"));

            College collegeToRemove = collegeRepository.findById(collegeId)
                    .orElseThrow(() -> new EntityNotFoundException("College not found"));


            university.getListOfColleges().remove(collegeToRemove); //why can't i remove this?
            collegeRepository.delete(collegeToRemove);
            universityRepository.save(university);
            logs.info("College is Demolished.");
            return true;

        } catch (Exception e) {
            logs.error(e.toString());
        }
        return false;
    }

    @Transactional
    @Override
    public List<CollegeResponseDto> bulkUpdate(int universityId, List<CollegeRequestDto> collegeRequestDtos) {
        try {
            University university = universityRepository.findById(universityId)
                    .orElseThrow(() -> new EntityNotFoundException("University not found"));

            Set<Integer> collegeToBeRemoved = university.getListOfColleges().stream()
                    .map(College::getCollegeId).
                    collect(Collectors.toSet());

            List<College> collegesToAdd  = new ArrayList<>();
            System.out.println("Actual Colleges to be removed : ");
            System.out.println(collegeToBeRemoved);
            for(CollegeRequestDto collegeRequestDto : collegeRequestDtos) {
                College updateCollege = CollegeMapper.COLLEGE_MAPPER.collegeRequestDtotoCollege(collegeRequestDto);
                if(collegeRequestDto.getCollegeId() != 0)
                    updateCollege.setCollegeId(collegeRequestDto.getCollegeId());
                updateCollege.setUniversity(university);
                collegesToAdd.add(updateCollege);
                collegeToBeRemoved.remove(collegeRequestDto.getCollegeId());
            }

            if(!collegeToBeRemoved.isEmpty()) {
                collegeRepository.deleteAllById(collegeToBeRemoved);
            }

            university.getListOfColleges().clear();
            university.getListOfColleges().addAll(collegesToAdd);

            University updatedUniversity = universityRepository.save(university);

            return updatedUniversity.getListOfColleges().stream()
                    .map(CollegeMapper.COLLEGE_MAPPER::collegeToCollegeResponseDto)
                    .collect(Collectors.toList());

        }catch(Exception e) {
            logs.error(e.toString());
        }
        return null;
    }

    @Override
    public CollegeResponseDto singleUpdate(int universityId, CollegeRequestDto collegeDTO) {
        try {
            University university = universityRepository.findById(universityId)
                    .orElseThrow(() -> new EntityNotFoundException("University not found"));
            College updateCollege = CollegeMapper.COLLEGE_MAPPER.collegeRequestDtotoCollege(collegeDTO);

            if(collegeDTO.getCollegeId() != 0)
                updateCollege.setCollegeId(collegeDTO.getCollegeId());

            updateCollege.setUniversity(university);
            College updatedCollege = collegeRepository.save(updateCollege);
            logs.info("College is updated!!");
            return CollegeMapper.COLLEGE_MAPPER.collegeToCollegeResponseDto(updatedCollege);
        } catch (Exception e) {
            logs.error(e.toString());
        }
        return null;
    }

    @Override
    public List<CollegeResponseDto> getColleges(int universityId) {
        Optional<University> univeristy = universityRepository.findById(universityId);
        if (univeristy.isPresent()) {
            List<College> colleges = univeristy.get().getListOfColleges();

            List<CollegeResponseDto> collegeResponseDTOS = new ArrayList<>();
            for(College c : colleges) {
                collegeResponseDTOS.add(CollegeMapper.COLLEGE_MAPPER.collegeToCollegeResponseDto(c));
            }
            logs.info("All Colleges are listed in brochure!!");
            return collegeResponseDTOS;
        }
        return null;
    }
}
