package com.firefry.superherocardfile.controller;

import com.firefry.superherocardfile.api.request.ComicsRequest;
import com.firefry.superherocardfile.api.response.ComicsResponse;
import com.firefry.superherocardfile.api.response.MarvelResponse;
import com.firefry.superherocardfile.domain.CharacterEntity;
import com.firefry.superherocardfile.repository.ComicRepository;
import com.firefry.superherocardfile.service.ComicsService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
public class ComicsController {
    private final ComicsService comicsService;

    @GetMapping("/v1/public/comics/")
    public Collection<MarvelResponse> getComicsList(){
        return comicsService.getFirst();
    }

    @GetMapping("/v1/public/comics/{comicsId}")
    public ComicsResponse getComicById(@PathVariable String comicId){
        return comicsService.getById(comicId);
    }

    @PostMapping("/v1/public/comics/")
    public ComicsResponse addComics(@RequestBody ComicsRequest request){
        return (ComicsResponse) comicsService.create(request);
    }
}
