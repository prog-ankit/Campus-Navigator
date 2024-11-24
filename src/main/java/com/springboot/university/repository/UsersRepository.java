package com.springboot.university.repository;

import com.springboot.university.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {
    public Users findByUsername(String username);
}
