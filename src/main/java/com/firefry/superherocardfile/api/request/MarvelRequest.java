package com.firefry.superherocardfile.api.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
//@AllArgsConstructor
public abstract class MarvelRequest {
    private String name;
    private String description;

    MarvelRequest(String name, String description){
        this.name = name;
        this.description = description;
    }
}
