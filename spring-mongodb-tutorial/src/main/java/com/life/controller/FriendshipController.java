package com.life.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Preconditions;
import com.life.domain.User;
import com.life.domain.relationship.Relationship;
import com.life.service.UserService;
import com.life.service.auth.impl.AuthServiceImpl;
import com.life.service.friend.impl.FriendServiceImpl;

@Controller
@RequestMapping("/friendship")
public class FriendshipController
{

    @Autowired
    private AuthServiceImpl authService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private FriendServiceImpl friendServiceImpl;

    @RequestMapping(value = "/all/{autId}", headers = "Accept=application/xml, application/json")
    public @ResponseBody
    List<User> getFriendList(@PathVariable(value = "autId") String autId)
    {
        Preconditions.checkNotNull(autId, "Invaild auth id");
        User user = authService.getUser(autId);
        
        return friendServiceImpl.getUserFriendList(user.getId());
    }

    @RequestMapping(value = "/add/{autId}", method = RequestMethod.POST, headers = "Accept=application/xml, application/json")
    public @ResponseBody
    String createFriendRequest(@RequestBody User friend, @PathVariable(value = "autId") String autId)
    {
        Preconditions.checkNotNull(autId, "Invaild auth id");
        User user = authService.getUser(autId);
        
        Relationship ownRelationship = friendServiceImpl.findByUserId(user.getId());
        ownRelationship.addPendingRequest(userService.findUserById(friend.getId()));
        friendServiceImpl.createRelationship(ownRelationship);
        
        Relationship friendRelationship = friendServiceImpl.findByUserId(friend.getId());
        friendRelationship.addPendingRequest(user);
        friendServiceImpl.createRelationship(friendRelationship);
        
        return "Friend Request Sended";
    }
    
    @RequestMapping(value = "/accept/{autId}", method = RequestMethod.POST, headers = "Accept=application/xml, application/json")
    public @ResponseBody
    String acceptFriendRequest(@RequestBody User friend, @PathVariable(value = "autId") String autId)
    {
        Preconditions.checkNotNull(autId, "Invaild auth id");
        User user = authService.getUser(autId);
        
        //accept friend request
        Relationship ownRelationship = friendServiceImpl.findByUserId(user.getId());
        ownRelationship.addFriend(userService.findUserById(friend.getId()));
        friendServiceImpl.createRelationship(ownRelationship);
        
        //update friend relationship list 
        Relationship friendRelationship = friendServiceImpl.findByUserId(friend.getId());
        friendRelationship.addFriend(user);
        friendServiceImpl.createRelationship(friendRelationship);
        
        //remove from pending list for both
        
        //add request to friend's friend request list
        return "Friend Request Accepted";
    }
    
    @RequestMapping(value = "/accept/{autId}", method = RequestMethod.POST, headers = "Accept=application/xml, application/json")
    public @ResponseBody
    String rejectFriendRequest(@RequestBody User friend, @PathVariable(value = "autId") String autId)
    {
        Preconditions.checkNotNull(autId, "Invaild auth id");
        User user = authService.getUser(autId);
        
        Relationship ownRelationship = friendServiceImpl.findByUserId(user.getId());
        ownRelationship.addRejectRequest(userService.findUserById(friend.getId()));
        friendServiceImpl.createRelationship(ownRelationship);
        
        Relationship friendRelationship = friendServiceImpl.findByUserId(friend.getId());
        friendRelationship.addRejectRequest(user);
        friendServiceImpl.createRelationship(friendRelationship);
        
        
        return "Friend Request Rejected";
    }

}
