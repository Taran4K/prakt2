package com.example.prakt2.repos;

import com.example.prakt2.models.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, Long> {
    Users findByUsername(String username);
}
