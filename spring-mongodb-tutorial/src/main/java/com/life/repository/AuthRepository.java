package com.life.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.life.domain.Auth;

public interface AuthRepository extends MongoRepository<Auth, String> {
	
	public Auth findById(String id);

    public Auth findByUniqId(String uniqId);
}
