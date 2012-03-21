package com.life.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.life.domain.Preparation;

public interface PreparationRepository extends MongoRepository<Preparation, String>
{

}
