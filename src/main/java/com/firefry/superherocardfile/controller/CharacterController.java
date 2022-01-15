package com.firefry.superherocardfile.controller;

import com.firefry.superherocardfile.api.request.CharacterRequest;
import com.firefry.superherocardfile.api.response.CharactersResponse;
import com.firefry.superherocardfile.exception.NotFoundCharacterEntityException;
import com.firefry.superherocardfile.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


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
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/characters")
    public Page<CharactersResponse> getCharactersList(@RequestParam Optional<Integer> page
                                                        , @RequestParam Optional<Integer> size
                                                        , @RequestParam Optional<String> sortBy){
        return characterService.getCharactersPage(page, size, sortBy);
    }

    @PostMapping("/characters")
    public ResponseEntity addCharacter(@RequestBody CharacterRequest request){
        characterService.create(request);
        return ResponseEntity.ok("Character successfully saved");
    }

}
