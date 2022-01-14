package com.firefry.superherocardfile.api.request;

import lombok.Data;

@Data
public class ComicsRequest{
    private String name;
    private String description;
    ComicsRequest(String name, String description){
        this.name = name;
        this.description = description;
    }
}
