package com.example.prakt2.repos;

import com.example.prakt2.models.PTS;
import org.springframework.data.repository.CrudRepository;

public interface PTSRepository extends CrudRepository<PTS, Long> {
    PTS findByNumber(String number);
}
