package com.firefry.superherocardfile.controller;

import com.firefry.superherocardfile.api.request.ComicsRequest;
import com.firefry.superherocardfile.entity.CharacterEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;

@RestController
public class ComicsController {

    @GetMapping("/v1/public/comics/")
    public Collection<CharacterEntity> getComicsList(){
        return Collections.emptyList();
    }

    @GetMapping("/v1/public/comics/{comicsId}")
    public String getComicById(@PathVariable String comicsId){
        return "From ComicsController - " + comicsId;
    }

    @PostMapping("/v1/public/comics/")
    public String addComics(@RequestBody ComicsRequest request){
        return request.getDescription();
    }
}
