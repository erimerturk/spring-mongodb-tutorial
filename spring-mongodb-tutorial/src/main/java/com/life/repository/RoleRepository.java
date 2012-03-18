package com.life.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.life.domain.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
}
