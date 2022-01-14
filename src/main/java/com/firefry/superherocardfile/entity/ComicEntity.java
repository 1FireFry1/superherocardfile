package com.firefry.superherocardfile.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "comics")
@Data
public class ComicEntity{

    @Id
    private String id;

    private String name;
    private String description;
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;

    public ComicEntity(){
        this.id = UUID.randomUUID().toString();
        LocalDateTime date = LocalDateTime.now();
        this.createdDateTime = date;
        this.modifiedDateTime = date;
    }

    public ComicEntity(String name, String description){
        this();
        this.name = name;
        this.description = description;
    }

}
