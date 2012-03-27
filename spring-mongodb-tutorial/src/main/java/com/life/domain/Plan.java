package com.life.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.google.common.collect.Lists;

import lombok.Data;


@Document
@XmlRootElement
@Data
public class Plan
{
    @Id
    private String id;
    private String name;
    
    @DBRef
    private List<Activity> activities = Lists.newArrayList();
    
    private String userName;
    
    public void addActivity(Activity activity)
    {
        this.activities.add(activity);
    }

}
