package com.life.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.life.domain.Feed;

public interface FeedRepository extends MongoRepository<Feed, String>
{

}
