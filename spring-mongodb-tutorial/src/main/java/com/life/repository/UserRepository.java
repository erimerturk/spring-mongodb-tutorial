package com.life.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.life.domain.User;

public interface UserRepository extends MongoRepository<User, String> {
	
	User findByUsername(String username);
	public User findById(String id);
}
