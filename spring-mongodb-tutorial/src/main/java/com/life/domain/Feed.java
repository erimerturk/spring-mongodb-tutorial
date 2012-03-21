package com.life.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@XmlRootElement
@Data
public class Feed
{
    @Id
    private String id;
    private String name;
    private Date time;
    

}
