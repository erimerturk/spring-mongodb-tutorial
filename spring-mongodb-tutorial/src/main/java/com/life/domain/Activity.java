package com.life.domain;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.google.common.collect.Lists;
import com.life.domain.feedable.Feedable;

@Document
@XmlRootElement
@Data
public class Activity implements Feedable
{
    @Id
    private String id;
    private String name;
    private int duration;
    
    @DBRef
    private User user;
    
    @DBRef
    private List<Preparation> preparations = Lists.newArrayList();
    
    @DBRef
    private List<Risk> risks = Lists.newArrayList();
    
    @DBRef
    private List<Feed> feeds = Lists.newArrayList();
    
    public void addPrepataion(Preparation preparation)
    {
        preparations.add(preparation);
    }
    
    public void addRisk(Risk risk)
    {
        risks.add(risk);
    }
    
    public void addFeed(Feed feed)
    {
        feeds.add(feed);
    }
    
    @Override
    public Feed feedOlustur()
    {
        Feed feed = new Feed();
        feed.setName("feed oldu");
        return feed;
    }

}
