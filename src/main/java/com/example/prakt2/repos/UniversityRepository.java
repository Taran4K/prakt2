package com.example.prakt2.repos;

import com.example.prakt2.models.University;
import org.springframework.data.repository.CrudRepository;

public interface UniversityRepository extends CrudRepository<University, Long> {
    University findByName(String name);
}
