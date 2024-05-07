package com.springboot.university.controller;

import com.springboot.university.requestresponse.request.CollegeRequestDto;
import com.springboot.university.requestresponse.response.CollegeResponseDto;
import com.springboot.university.service.CollegeService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/")
public class CollegeController {
    @Autowired
    CollegeService collegeServices;

    @PostMapping("university/{university_id}/college/add")
    public ResponseEntity<CollegeResponseDto> addCollege(@PathVariable("university_id") int universityId, @RequestBody CollegeRequestDto collegeRequestDto) {
        CollegeResponseDto collegeResponseDto = collegeServices.addCollege(universityId, collegeRequestDto);
        if(collegeResponseDto != null)
            return ResponseEntity.ok(collegeResponseDto);
        return ResponseEntity.status(404).body(null);
    }

    @GetMapping("university/{univeristy_id}/colleges")
    public ResponseEntity<List<CollegeResponseDto>> getAllColleges(@PathVariable("univeristy_id") int universityId) {
        return ResponseEntity.ok(collegeServices.getColleges(universityId));
    }

    @DeleteMapping("university/{university_id}/college/delete/{college_id}")
    public ResponseEntity<String> deleteCollege(@PathVariable("university_id") int universityId, @PathVariable("college_id") int collegeId){
        boolean response = collegeServices.deleteCollege(universityId, collegeId);
        if(response)
            return ResponseEntity.status(204).body(String.valueOf(response));
        return ResponseEntity.status(404).body("No Such Id Present. ");
    }

    @PutMapping("university/{university_id}/college/update")
    public ResponseEntity<CollegeResponseDto> singleUpdate(@PathVariable("university_id") int universityId,@RequestBody CollegeRequestDto collegeRequestDto) {
        CollegeResponseDto collegeResponseDto = collegeServices.singleUpdate(universityId,collegeRequestDto);
        if(collegeResponseDto != null) {
            return ResponseEntity.ok(collegeResponseDto);
        }
        return ResponseEntity.status(404).body(null);
    }

    @PutMapping("university/{university_id}/colleges/update")
    public ResponseEntity<List<CollegeResponseDto>> bulkUpdate(@PathVariable("university_id") int universityId,@RequestBody List<CollegeRequestDto> collegeRequestDto) {
        List<CollegeResponseDto> collegeResponseDto = collegeServices.bulkUpdate(universityId,collegeRequestDto);
        if(collegeResponseDto != null) {
            return ResponseEntity.ok(collegeResponseDto);
        }
        return ResponseEntity.status(404).body(null);
    }


}
