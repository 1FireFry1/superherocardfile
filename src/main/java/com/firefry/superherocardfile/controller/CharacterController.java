package com.firefry.superherocardfile.controller;

import com.firefry.superherocardfile.api.request.CharacterRequest;
import com.firefry.superherocardfile.api.response.CharactersResponse;
import com.firefry.superherocardfile.exception.NotFoundCharacterEntityException;
import com.firefry.superherocardfile.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping("/v1/public")

public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping("/characters/{characterId}")
    public ResponseEntity getById(@PathVariable String characterId){
        try {
            return ResponseEntity.ok().body(characterService.getById(characterId));
        } catch (NotFoundCharacterEntityException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/characters")
    public Iterable<CharactersResponse> getCharactersList(){
        return characterService.getCharactersList();
    }

    @PostMapping("/characters")
    public ResponseEntity addCharacter(@RequestBody CharacterRequest request){
        characterService.create(request);
        return ResponseEntity.ok("Character successfully saved");
    }

}
