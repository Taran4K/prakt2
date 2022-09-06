package com.example.prakt2.repos;

import com.example.prakt2.models.Students;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface StudentsRepository extends CrudRepository<Students, Long> {
    List<Students> findBySurnameContains(String surname);
    List<Students> findBySurname(String surname);
}
