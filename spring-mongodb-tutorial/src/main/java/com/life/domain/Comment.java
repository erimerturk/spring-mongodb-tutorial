package com.life.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@XmlRootElement
@Data
public class Comment
{
    @Id
    private String id;
    private User actor;
    private Date published;

}
