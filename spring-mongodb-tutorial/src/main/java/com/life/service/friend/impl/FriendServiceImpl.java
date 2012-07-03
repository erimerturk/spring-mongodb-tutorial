package com.life.service.friend.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.life.domain.User;
import com.life.domain.relationship.Relationship;
import com.life.repository.RelationshipRepository;
import com.life.repository.UserRepository;

@Service("friendService")
public class FriendServiceImpl
{

    @Autowired
    private RelationshipRepository relationshipRepository;
    
    @Autowired
    private UserRepository userRepository;

    public Relationship findByUserId(String userId)
    {
        Relationship relationship = relationshipRepository.findByUserId(userId);
        if(relationship == null)
        {
            relationship = Relationship.createEmptyRelationship(userId);
        }
        
        return relationship;
    }
    
    public List<User> getUserFriendList(String userId)
    {
        return relationshipRepository.findByUserId(userId).getFriends();
    }

    public Relationship createRelationship(Relationship friendRelationship)
    {
        userRepository.save(friendRelationship.getFriends());
        return relationshipRepository.save(friendRelationship);
    }

}
