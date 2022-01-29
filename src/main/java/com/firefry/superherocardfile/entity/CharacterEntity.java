package com.firefry.superherocardfile.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "characters")
@Data
public class CharacterEntity{

    @Id
    private String id;

    private String name;    //Name of character
    private String description; //Description of character
    private String imageName;
    private List<String> comicsList;    //List of comics where character was
    private LocalDateTime createdDateTime;  //Date and time when this character created
    private LocalDateTime modifiedDateTime; //Date and time when this character modified

    public CharacterEntity(){
        LocalDateTime date = LocalDateTime.now();
        this.createdDateTime = date;
        this.modifiedDateTime = date;
    }

    public CharacterEntity(String name, String description, List<String> comicsList){
        this();
        this.name = name;
        this.description = description;
        this.comicsList = comicsList;
    }

}
