package com.springboot.university.repository;

import com.springboot.university.models.University;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UniversityRepository extends JpaRepository<University,Integer> {
}
