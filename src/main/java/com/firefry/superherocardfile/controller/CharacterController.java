package com.firefry.superherocardfile.controller;

import com.firefry.superherocardfile.api.request.CharacterRequest;
import com.firefry.superherocardfile.entity.CharacterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;

@RestController()
public class CharacterController {

    CharacterEntity characterEntity;

    @GetMapping("/v1/public/characters/")
    public Collection<CharacterEntity> getCharactersList(){
        return Collections.emptyList();
    }

    @GetMapping("/v1/public/characters/{characterId}")
    public String getCharacterById(@PathVariable String characterId){
        return "From CharacterController - " + characterId;
    }

    @PostMapping("/v1/public/characters/{characterId}")
    public String addCharacter(@RequestBody CharacterRequest request){
        return request.getDescription();
    }



}
