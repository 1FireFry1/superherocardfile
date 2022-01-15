package com.firefry.superherocardfile.controller;

import com.firefry.superherocardfile.api.request.ComicsRequest;
import com.firefry.superherocardfile.api.response.ComicsResponse;
import com.firefry.superherocardfile.exception.NotFoundComicEntityException;
import com.firefry.superherocardfile.service.ComicsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/public")
@RequiredArgsConstructor
public class ComicsController {

    @Autowired
    private final ComicsService comicsService;

    @GetMapping("/comics")
    public Page<ComicsResponse> getComicsList(@RequestParam Optional<Integer> page
            , @RequestParam Optional<Integer> size
            , @RequestParam Optional<String> sortBy){
        return comicsService.getComicsPage(page, size, sortBy);
    }

    @GetMapping("/comics/{comicId}")
    public ResponseEntity getComicById(@PathVariable String comicId){
        try {
            return ResponseEntity.ok().body(comicsService.getById(comicId));
        } catch (NotFoundComicEntityException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
//            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/comics")
    public ResponseEntity addComic(@RequestBody ComicsRequest request){
        comicsService.create(request);
        return ResponseEntity.ok("Comic successfully saved");
    }

}
