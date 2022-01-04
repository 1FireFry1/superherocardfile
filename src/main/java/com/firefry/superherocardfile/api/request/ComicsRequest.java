package com.firefry.superherocardfile.api.request;

import lombok.Data;

//@Data
//public class ComicsRequest{
public class ComicsRequest extends MarvelRequest{
//    private String name;
//    private String description;
    ComicsRequest(String name, String description){
        super(name, description);
    }
}
