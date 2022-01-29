package com.firefry.superherocardfile.controller;

import com.firefry.superherocardfile.api.request.ComicsRequest;
import com.firefry.superherocardfile.api.response.CharactersResponse;
import com.firefry.superherocardfile.api.response.ComicsResponse;
import com.firefry.superherocardfile.exception.NotFoundComicEntityException;
import com.firefry.superherocardfile.service.ComicsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/v1/public/comics")
@RequiredArgsConstructor
public class ComicsController {

    @Autowired
    private ComicsService comicsService;

    @GetMapping("/{comicId}")
    public ResponseEntity getComicById(@PathVariable String comicId){
        try {
            return ResponseEntity.ok().body(comicsService.getById(comicId.trim()));
        } catch (NotFoundComicEntityException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("")
    public Page<ComicsResponse> getComicsList(@RequestParam Optional<Integer> page
                                            , @RequestParam Optional<Integer> size
                                            , @RequestParam Optional<String> sortBy){
        return comicsService.getComicsPage(page, size, sortBy);
    }

    @GetMapping("/{comicId}/characters")
    public Page<CharactersResponse> getCharactersPageByComicId(@PathVariable String comicId,
                                                               @RequestParam Optional<Integer> page,
                                                               @RequestParam Optional<Integer> size,
                                                               @RequestParam Optional<String> sortBy){
        return comicsService.getComicsPageByCharacterId(comicId, page, size, sortBy);
    }

    @DeleteMapping("{comicId}")
    public ResponseEntity<ComicsResponse> deleteComicById(@PathVariable String comicId){
        comicsService.deleteById(comicId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("")
    public ResponseEntity<ComicsResponse> addComic(@RequestBody ComicsRequest request){
        return ResponseEntity.ok(comicsService.create(request));
    }

}
