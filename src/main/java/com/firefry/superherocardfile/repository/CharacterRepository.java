package com.firefry.superherocardfile.repository;

import com.firefry.superherocardfile.domain.CharacterEntity;
import com.firefry.superherocardfile.domain.MarvelEntity;
import com.firefry.superherocardfile.exception.NotFoundCharacterEntityException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
@Qualifier
public class CharacterRepository implements MarvelRepository{
    private final Map<String, CharacterEntity> storage = new ConcurrentHashMap<>();

    @Override
    public CharacterEntity getById(String id) {
        CharacterEntity characterEntity = storage.get(id);
        if (characterEntity == null){
            throw new NotFoundCharacterEntityException("Character not found with id=" + id);
        }
        return characterEntity;
    }

    @Override
    public List<MarvelEntity> getList() {
        return storage.values().stream()
                .sorted(Comparator.comparing(CharacterEntity::getName))
                .collect(Collectors.toList());
    }

    @Override
    public void add(MarvelEntity marvelEntity) {
        storage.put(marvelEntity.getId(), (CharacterEntity) marvelEntity);
    }
}
