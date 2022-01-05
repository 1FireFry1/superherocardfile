package com.firefry.superherocardfile.repository;

import com.firefry.superherocardfile.domain.CharacterEntity;
import com.firefry.superherocardfile.domain.ComicEntity;
import com.firefry.superherocardfile.domain.MarvelEntity;
import com.firefry.superherocardfile.exception.NotFoundComicEntityException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
@Qualifier
public class ComicRepository implements MarvelRepository{
    private final Map<String, ComicEntity> storage = new ConcurrentHashMap<>();


    @Override
    public ComicEntity getById(String id) {
        ComicEntity comicEntity = storage.get(id);
        if (comicEntity == null){
            throw new NotFoundComicEntityException("Comic not found with id=" + id);
        }
        return comicEntity;
    }

    @Override
    public List<MarvelEntity> getList() {
        return storage.values().stream()
                .sorted(Comparator.comparing(ComicEntity::getName))
                .collect(Collectors.toList());
    }

    @Override
    public void add(MarvelEntity marvelEntity) {
        storage.put(marvelEntity.getId(), (ComicEntity) marvelEntity);
    }
}
