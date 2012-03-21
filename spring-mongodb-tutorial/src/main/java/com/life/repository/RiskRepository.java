package com.life.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.life.domain.Risk;

public interface RiskRepository extends MongoRepository<Risk, String>
{

}
