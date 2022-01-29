package com.firefry.superherocardfile.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "photos")
public class PhotoEntity {
    @Id
    private String id;
    private String imageName;   //file name
    private Binary content; //file content
    private String contentType; //file type
    private long size;  //file size
    private LocalDateTime createdDateTime;  // upload time

    public PhotoEntity(){
        createdDateTime = LocalDateTime.now();
    }
    public PhotoEntity(String imageName){
        this.imageName = imageName;
    }
}
