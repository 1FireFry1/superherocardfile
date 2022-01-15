package com.firefry.superherocardfile.service;

import com.firefry.superherocardfile.api.request.ComicsRequest;
import com.firefry.superherocardfile.api.response.ComicsResponse;
import com.firefry.superherocardfile.entity.ComicEntity;
import com.firefry.superherocardfile.exception.NotFoundComicEntityException;
import com.firefry.superherocardfile.repository.ComicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ComicsService{
    private final ComicRepository comicRepository;

    public ComicsResponse getById(String comicId) throws NotFoundComicEntityException {
        Optional<ComicEntity> optionalComicEntity = comicRepository.findById(comicId);
        if (optionalComicEntity.isEmpty()){
            throw new NotFoundComicEntityException("Comic not found with id=" + comicId);
        }
        return ComicsResponse.toComicsResponse(optionalComicEntity.get());
    }

    public Page<ComicsResponse> getComicsPage(Optional<Integer> page, Optional<Integer> size, Optional<String> sortBy) {
        Page<ComicsResponse> comicsResponsePage = comicRepository.findAll(PageRequest.of(page.orElse(0)
                , size.orElse(3)
                , Sort.Direction.ASC, sortBy.orElse("title"))).map(ComicsResponse::toComicsResponse);
        return comicsResponsePage;
    }

    public ComicsResponse create(ComicsRequest request) {
        ComicEntity entity = new ComicEntity(request.getName(), request.getDescription());
        comicRepository.save(entity);
        return ComicsResponse.toComicsResponse(entity);
    }
//    ComicsWithFilterResponse filter(ComicsRequest request);
}
