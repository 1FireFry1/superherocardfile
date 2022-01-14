package com.firefry.superherocardfile.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;

@Document(collection = "characters")
@Data
public class CharacterEntity{

    @Id
    private String id;

    private String name;
    private String description;
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;

    public CharacterEntity(){
        this.id = UUID.randomUUID().toString();
        LocalDateTime date = LocalDateTime.now();
        this.createdDateTime = date;
        this.modifiedDateTime = date;
    }

    public CharacterEntity(String name, String description){
        this();
        this.name = name;
        this.description = description;
    }

}
