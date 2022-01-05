package com.firefry.superherocardfile.service;

import com.firefry.superherocardfile.api.request.MarvelRequest;
import com.firefry.superherocardfile.api.response.ComicsResponse;
import com.firefry.superherocardfile.api.response.MarvelResponse;
import com.firefry.superherocardfile.domain.ComicEntity;
import com.firefry.superherocardfile.domain.MarvelEntity;
import com.firefry.superherocardfile.repository.ComicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ComicsService implements SuperHeroCardFileService {
    private final ComicRepository repository;

    public ComicsResponse getById(String comicId) {
        ComicEntity entity = repository.getById(comicId);
        return new ComicsResponse(entity.getId(), entity.getName(), entity.getDescription());
    }

    public List<MarvelResponse> getFirst() {
        List<MarvelEntity> comicEntityList = repository.getList();
        return comicEntityList.stream().map(marvelEntity ->
                new ComicsResponse(marvelEntity.getId(), marvelEntity.getName(), marvelEntity.getDescription()))
                .collect(Collectors.toList());
    }

    @Override
    public MarvelResponse create(MarvelRequest marvelRequest) {
        MarvelEntity entity = new ComicEntity(marvelRequest.getName(), marvelRequest.getDescription());
        repository.add(entity);
        return new ComicsResponse(entity.getId(), entity.getName(), entity.getDescription());
    }
//    ComicsWithFilterResponse filter(ComicsRequest request);
}
