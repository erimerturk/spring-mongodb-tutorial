package com.life.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.life.domain.Activity;

public interface ActivityRepository extends MongoRepository<Activity, String> {
	
	Activity findByName(String name);
}
