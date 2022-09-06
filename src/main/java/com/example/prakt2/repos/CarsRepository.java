package com.example.prakt2.repos;

import com.example.prakt2.models.Cars;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CarsRepository extends CrudRepository<Cars, Long> {
    List<Cars> findByMarkContains(String mark);
    List<Cars> findByMark(String mark);
}
