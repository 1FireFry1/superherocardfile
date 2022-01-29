package com.firefry.superherocardfile.api.request;

import lombok.Data;

import java.util.List;

@Data
public class CharacterRequest{
    private String name;
    private String description;
    private List<String> comicsList;

    public CharacterRequest(){}

    public CharacterRequest(String name, String description, List<String> comicsList) {
        this.name = name;
        this.description = description;
        this.comicsList = comicsList;
    }

    public CharacterRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
