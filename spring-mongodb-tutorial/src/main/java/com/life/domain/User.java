package com.life.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.google.common.collect.Lists;

@Document
@XmlRootElement
@Data
public class User {
	
	@Id
	private String id;
	
	private String firstName;
	private String lastName;
	
	private String username;
	private String password;
	
	@DBRef
	private Role role;

	@DBRef
    private List<User> friends = Lists.newArrayList();

	@DBRef
    private List<Plan> plans = Lists.newArrayList();

    public void addFriend(User friend)
    {
        this.friends.add(friend);
    }

    public void addPlan(Plan plan)
    {
        this.plans.add(plan);
    }
}
