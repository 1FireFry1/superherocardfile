package com.firefry.superherocardfile.api.response;


import com.firefry.superherocardfile.entity.CharacterEntity;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class CharactersResponse{
    private String id;
    private String name;
    private String description;

    public static CharactersResponse toCharactersResponse(CharacterEntity entity){
        CharactersResponse response = new CharactersResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setDescription(entity.getDescription());
        return response;
    }

    public CharactersResponse(){

    }
}
