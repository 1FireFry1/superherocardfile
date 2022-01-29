package com.firefry.superherocardfile.controller;

import com.firefry.superherocardfile.api.request.CharacterRequest;
import com.firefry.superherocardfile.api.response.CharactersResponse;
//import com.firefry.superherocardfile.exception.NotExistDirectoryException;
import com.firefry.superherocardfile.api.response.ComicsResponse;
import com.firefry.superherocardfile.exception.NotFoundCharacterEntityException;
import com.firefry.superherocardfile.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RestController()
@RequestMapping("/v1/public/characters")

public class CharacterController {

    @Autowired
    private CharacterService characterService;

    @GetMapping("/{characterId}")
    public ResponseEntity getById(@PathVariable String characterId){
        try {
            return ResponseEntity.ok().body(characterService.getById(characterId.trim()));
        } catch (NotFoundCharacterEntityException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("")
    public Page<CharactersResponse> getCharactersList(@RequestParam Optional<Integer> page
                                                    , @RequestParam Optional<Integer> size
                                                    , @RequestParam Optional<String> sortBy){
        return characterService.getCharactersPage(page, size, sortBy);
    }

    @GetMapping("/{characterId}/comics")
    public Page<ComicsResponse> getComicsPageByCharacterId(@PathVariable String characterId,
                                                           @RequestParam Optional<Integer> page,
                                                           @RequestParam Optional<Integer> size,
                                                           @RequestParam Optional<String> sortBy){
        return characterService.getComicsPageByCharacterId(characterId, page, size, sortBy);
    }

    @DeleteMapping("/{characterId}")
    public ResponseEntity<CharactersResponse> deleteCharacterById(@PathVariable String characterId){
        characterService.deleteById(characterId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("")
    public ResponseEntity addCharacter(@RequestBody CharacterRequest request){
//        try {
            characterService.create(request);
//        } catch (NotExistDirectoryException e) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
//        }
        return ResponseEntity.ok("Character successfully saved");
    }

}
