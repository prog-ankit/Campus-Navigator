package com.springboot.university.controller;

import com.springboot.university.requestresponse.request.UniversityRequestDto;
import com.springboot.university.requestresponse.response.UniversityResponseDto;
import com.springboot.university.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/university")
public class UniversityController {

    @Autowired
    UniversityService universityServices;

    @PostMapping("/add")
    ResponseEntity<UniversityResponseDto> addUniversity(@RequestBody UniversityRequestDto universityRequestDto) {
        UniversityResponseDto responseDto = universityServices.addUniversity(universityRequestDto);

        if(responseDto != null) {
            return ResponseEntity.ok(responseDto);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @GetMapping("")
    ResponseEntity<List<UniversityResponseDto>> getAllUniversity() {
        List<UniversityResponseDto> responseDtos = universityServices.getAllUniversities();
        return ResponseEntity.ok(responseDtos);
    }
    @GetMapping("/{university_id}")
    ResponseEntity<UniversityResponseDto> getUniversity(@PathVariable("university_id") int universityId) {
        UniversityResponseDto universityResponseDto = universityServices.getUniversity(universityId);
        if(universityResponseDto != null)
            return ResponseEntity.ok(universityResponseDto);
        return ResponseEntity.status(404).body(null);
    }

    @DeleteMapping("/{university_id}")
    ResponseEntity<String> deleteUniversity(@PathVariable("university_id") int universityId) {
        boolean response = universityServices.deleteUniversity(universityId);
        if(response)
            return ResponseEntity.status(204).body(String.valueOf(true));
        return ResponseEntity.status(404).body("Deletion Failed. ");
    }

    @PutMapping("/{university_id}")
    ResponseEntity<UniversityResponseDto> updateUniversity(@PathVariable("university_id") int universityId, @RequestBody UniversityRequestDto universityRequestDto) {
        UniversityResponseDto universityResponseDto = universityServices.updateUniversity(universityId, universityRequestDto);
        if(universityResponseDto != null) {
            return ResponseEntity.ok(universityResponseDto);
        }
        return ResponseEntity.status(404).body(null);
    }

    @PutMapping("/updatebulk")
    ResponseEntity<List<UniversityResponseDto>> updateAllUniversities(@RequestBody List<UniversityRequestDto> universityRequestDtos) {
        return ResponseEntity.ok(universityServices.udpateBulkUniversity(universityRequestDtos));
    }
}