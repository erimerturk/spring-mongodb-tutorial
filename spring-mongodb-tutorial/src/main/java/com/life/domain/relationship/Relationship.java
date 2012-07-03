package com.life.domain.relationship;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.google.common.collect.Lists;
import com.life.domain.User;

@Document
@XmlRootElement
@Data
public class Relationship
{

    @Id
    private String id;

    @Indexed
    private String userId;

    @DBRef
    private List<User> friends = Lists.newArrayList();
    
    @DBRef
    private List<User> pendingRequests = Lists.newArrayList();
    
    @DBRef
    private List<User> rejectedRequests = Lists.newArrayList();

    public void addFriend(User friend)
    {
        this.friends.add(friend);
    }
    
    public void addPendingRequest(User friend)
    {
        this.pendingRequests.add(friend);
    }
    
    public void removePendingRequest(User friend)
    {
        //TODO : need to overrite equals method for User 
        this.pendingRequests.remove(friend);
    }
    
    public void addRejectRequest(User friend)
    {
        this.rejectedRequests.add(friend);
    }
    
    public static Relationship createEmptyRelationship(String userId)
    {
        Relationship relationship = new Relationship();
        relationship.setId(userId);
        return relationship;
    }
}
