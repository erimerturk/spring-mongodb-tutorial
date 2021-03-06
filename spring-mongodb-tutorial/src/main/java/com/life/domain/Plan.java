package com.life.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

import org.codehaus.jackson.annotate.JsonManagedReference;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.google.common.collect.Lists;


@Document
@XmlRootElement
@Data
public class Plan
{
    @Id
    private String id;
    private String name;
    private String uniqId;
    
    private String userId;
    
    @DBRef
    private List<Activity> activities = Lists.newArrayList();
    
    public void addActivity(Activity activity)
    {
        this.activities.add(activity);
    }

}
