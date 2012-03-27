package com.life.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.life.domain.Plan;

public interface PlanRepository extends MongoRepository<Plan, String>
{
    public Plan findById(String id);
}
