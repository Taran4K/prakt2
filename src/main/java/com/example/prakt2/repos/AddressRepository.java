package com.example.prakt2.repos;

import com.example.prakt2.models.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
    Address findByStreet(String street);
}