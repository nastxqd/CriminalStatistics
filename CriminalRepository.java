package com.example.criminal3.repositories;

import com.example.criminal3.Criminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface CriminalRepository extends JpaRepository<Criminal, Integer> {
    List<Criminal> findById(int id);
    List<Criminal> findBySubject(String subject);
}
