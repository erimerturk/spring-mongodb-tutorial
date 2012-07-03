package com.life.domain;

import java.util.UUID;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@XmlRootElement
@Data
public class Auth{
	
    @Id
    private String id;
    private String uniqId;
	
    @DBRef
    @JsonBackReference
	private User user;

   public static Auth createUnique()
   {
      Auth auth = new Auth();
      auth.setUniqId(UUID.randomUUID().toString());
      return auth;
   }
	
	
}
