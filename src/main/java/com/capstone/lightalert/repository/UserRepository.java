package com.capstone.lightalert.repository;

import com.capstone.lightalert.model.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<Users, Long> {
    Optional<Users> findByEmail(String email);

    Users findBySystemId(String systemId);
}
