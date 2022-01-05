package com.firefry.superherocardfile.domain;

import javax.persistence.Entity;

//@Entity
public class CharacterEntity extends MarvelEntity{

    public CharacterEntity() {
        super();
    }

    public CharacterEntity(String name, String description){
        super(name, description);
    }

}
