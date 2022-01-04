package com.firefry.superherocardfile.entity;



import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class CharacterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    private String description;
    private LocalDateTime createdDateTime;
    private LocalDateTime modifiedDateTime;

    public CharacterEntity(){
        LocalDateTime date = LocalDateTime.now();
//        this.id = UUID.randomUUID().toString();
        this.createdDateTime = date;
        this.modifiedDateTime = date;
    }

    public CharacterEntity(String name, String description){
        this();
        this.name = name;
        this.description = description;
    }

}
