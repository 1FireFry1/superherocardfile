package com.firefry.superherocardfile.api.request;

import lombok.Data;

//@Data
//public class CharacterRequest{
public class CharacterRequest extends MarvelRequest{
//    private String name;
//    private String description;
    CharacterRequest(String name, String description){
        super(name, description);
    }
}
