package com.firefry.superherocardfile.domain;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public abstract class MarvelEntity {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @NotNull
    @NotBlank
    private String name;
    @NotNull
    private String description;
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;

    public MarvelEntity(){
        this.id = UUID.randomUUID().toString();
        LocalDateTime date = LocalDateTime.now();
        this.createdDateTime = date;
        this.modifiedDateTime = date;
    }

    public MarvelEntity(String name, String description){
        this();
        this.name = name;
        this.description = description;
    }
}
