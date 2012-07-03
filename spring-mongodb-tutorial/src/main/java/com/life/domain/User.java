package com.life.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.codehaus.jackson.annotate.JsonManagedReference;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.google.common.collect.Lists;

@Document
@XmlRootElement
@Data
@EqualsAndHashCode(of={"id", "username"})
public class User
{

    @Id
    private String id;

    private String firstName;
    private String lastName;

    @Indexed
    private String username;

    private String password;

    @DBRef
    private Role role;

    @DBRef
    @JsonManagedReference
    private Auth auth;

    @DBRef
    @JsonManagedReference
    private List<Plan> plans = Lists.newArrayList();;

    public void addPlan(Plan plan)
    {
        this.plans.add(plan);
    }

    public boolean isPasswordMatched(String password)
    {
        return getPassword().equals(password);
    }

}
