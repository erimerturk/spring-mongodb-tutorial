package com.life.domain;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@XmlRootElement
@Data
public class Preparation
{
    @Id
    private String id;
    private String name;
    private boolean finished;
    

}
