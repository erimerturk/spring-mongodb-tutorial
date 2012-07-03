package com.life.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.life.domain.relationship.Relationship;

public interface RelationshipRepository extends MongoRepository<Relationship, String>
{

    public Relationship findByUserId(String userId);

}
