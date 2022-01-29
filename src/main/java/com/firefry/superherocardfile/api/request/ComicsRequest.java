package com.firefry.superherocardfile.api.request;

import lombok.Data;

import java.util.List;

@Data
public class ComicsRequest{
    private String title;
    private String description;
    private List<String> charactersList;

    public ComicsRequest(){}

    ComicsRequest(String title, String description, List<String> charactersList){
        this.title = title;
        this.description = description;
        this.charactersList = charactersList;
    }
    ComicsRequest(String title, String description){
        this.title = title;
        this.description = description;
    }
}
