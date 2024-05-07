package com.springboot.university.repository;

import com.springboot.university.models.College;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollegeRepository extends JpaRepository<College,Integer> {
}
