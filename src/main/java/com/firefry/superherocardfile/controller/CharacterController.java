package com.firefry.superherocardfile.controller;

import com.firefry.superherocardfile.api.request.CharacterRequest;
import com.firefry.superherocardfile.api.response.CharactersResponse;
import com.firefry.superherocardfile.api.response.MarvelResponse;
import com.firefry.superherocardfile.domain.ComicEntity;
import com.firefry.superherocardfile.service.CharacterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;

@RestController()
@RequiredArgsConstructor
public class CharacterController {

    private final CharacterService characterService;

    @GetMapping("/v1/public/characters/")
    public Collection<MarvelResponse> getCharactersList(){
        return characterService.getFirst();
//        return Collections.emptyList();
    }

    @GetMapping("/v1/public/characters/{characterId}")
    public CharactersResponse getCharacterById(@PathVariable String characterId){
        return characterService.getById(characterId);
    }

    @GetMapping("/v1/public/characters/{characterId}/characters")
    public Collection<ComicEntity> getComicsList(@PathVariable String characterId){
        return Collections.emptyList();
    }

    @PostMapping("/v1/public/characters/")
    public CharactersResponse addCharacter(@RequestBody CharacterRequest request){
        return (CharactersResponse) characterService.create(request);
    }



}
