package com.firefry.superherocardfile.service;

import com.firefry.superherocardfile.api.request.ComicsRequest;
import com.firefry.superherocardfile.api.response.ComicsResponse;
import com.firefry.superherocardfile.entity.ComicEntity;
import com.firefry.superherocardfile.exception.NotFoundComicEntityException;
import com.firefry.superherocardfile.repository.ComicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<ComicsResponse> getComicsList() {
        List<ComicEntity> comicEntityList = comicRepository.findAll();
        return comicEntityList.stream().map(ComicsResponse::toComicsResponse)
                .collect(Collectors.toList());
    }

    public ComicsResponse create(ComicsRequest request) {
        ComicEntity entity = new ComicEntity(request.getName(), request.getDescription());
        comicRepository.save(entity);
        return ComicsResponse.toComicsResponse(entity);
    }
//    ComicsWithFilterResponse filter(ComicsRequest request);
}
