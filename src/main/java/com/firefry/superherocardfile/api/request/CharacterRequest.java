package com.firefry.superherocardfile.api.request;

import lombok.Data;

@Data
public class CharacterRequest{
    private String name;
    private String description;

    public CharacterRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
