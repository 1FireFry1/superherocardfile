package com.firefry.superherocardfile.controller;

import com.firefry.superherocardfile.api.request.CharacterRequest;
import com.firefry.superherocardfile.api.response.CharactersResponse;
import com.firefry.superherocardfile.api.response.ComicsResponse;
import com.firefry.superherocardfile.exception.NotFoundCharacterEntityException;
import com.firefry.superherocardfile.service.CharacterService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


@RestController()
@RequestMapping("/v1/public/characters")
@Tag(name = "Characters")
public class CharacterController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CharacterController.class);

    @Autowired
    private CharacterService characterService;

    @GetMapping("/{characterId}")
    public ResponseEntity getById(@PathVariable String characterId){
        try {
            LOGGER.info("PathVariable=" + characterId);
            return ResponseEntity.ok().body(characterService.getById(characterId.trim()));
        } catch (NotFoundCharacterEntityException e) {
            LOGGER.error(e.getMessage());
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
        try {
            return characterService.getComicsPageByCharacterId(characterId.trim(), page, size, sortBy);
        } catch (NotFoundCharacterEntityException e) {
            return (Page<ComicsResponse>) ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{characterId}")
    public ResponseEntity<CharactersResponse> deleteCharacterById(@PathVariable String characterId){
        characterService.deleteById(characterId.trim());
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<CharactersResponse> addCharacter(@RequestBody CharacterRequest request){
        return ResponseEntity.ok().body(characterService.addCharacter(request));
    }

}
