package com.firefry.superherocardfile.controller;

import com.firefry.superherocardfile.api.request.ComicsRequest;
import com.firefry.superherocardfile.api.response.ComicsResponse;
import com.firefry.superherocardfile.exception.NotFoundComicEntityException;
import com.firefry.superherocardfile.service.ComicsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/public")
@RequiredArgsConstructor
public class ComicsController {

    @Autowired
    private final ComicsService comicsService;

    @GetMapping("/comics")
    public Iterable<ComicsResponse> getComicsList(){
        return comicsService.getComicsList();
    }

    @GetMapping("/comics/{comicsId}")
    public ResponseEntity getComicById(@PathVariable String comicId){
        try {
            return ResponseEntity.ok().body(comicsService.getById(comicId));
        } catch (NotFoundComicEntityException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/comics")
    public ResponseEntity addComic(@RequestBody ComicsRequest request){
        comicsService.create(request);
        return ResponseEntity.ok("Comic successfully saved");
    }

}
