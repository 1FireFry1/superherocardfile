package com.firefry.superherocardfile.service;

import com.firefry.superherocardfile.api.response.CharactersResponse;
import com.firefry.superherocardfile.api.response.MarvelResponse;

import java.util.List;

public class CharacterService implements SuperHeroCardFileService{
    public CharactersResponse getById(String characterId) {
        return null;
    }

    public List<MarvelResponse> getFirst(int amount) {
        return null;
    }
//    CharactersWithFilterResponse filter(CharacterRequest request);
}
