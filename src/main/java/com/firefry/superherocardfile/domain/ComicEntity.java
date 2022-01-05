package com.firefry.superherocardfile.domain;

import javax.persistence.Entity;

//@Entity
public class ComicEntity extends MarvelEntity{

    public ComicEntity() {
        super();
    }

    public ComicEntity(String name, String description){
        super(name, description);
    }

}
