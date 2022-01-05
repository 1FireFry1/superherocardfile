package com.firefry.superherocardfile.service;

import com.firefry.superherocardfile.api.request.MarvelRequest;
import com.firefry.superherocardfile.api.response.CharactersResponse;
import com.firefry.superherocardfile.api.response.MarvelResponse;
import com.firefry.superherocardfile.domain.CharacterEntity;
import com.firefry.superherocardfile.domain.ComicEntity;
import com.firefry.superherocardfile.domain.MarvelEntity;
import com.firefry.superherocardfile.repository.CharacterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CharacterService implements SuperHeroCardFileService{
    private final CharacterRepository repository;

    @Override
    public CharactersResponse getById(String characterId) {
        CharacterEntity entity = repository.getById(characterId);
        return new CharactersResponse(entity.getId(), entity.getName(), entity.getDescription());
    }

    @Override
    public List<MarvelResponse> getFirst() {
        List<MarvelEntity> characterEntityList = repository.getList();

        return characterEntityList.stream().map(marvelEntity ->
                new CharactersResponse(marvelEntity.getId(), marvelEntity.getName(), marvelEntity.getDescription()))
                .collect(Collectors.toList());
    }

    @Override
    public MarvelResponse create(MarvelRequest marvelRequest) {
        MarvelEntity entity = new CharacterEntity(marvelRequest.getName(), marvelRequest.getDescription());
        repository.add(entity);
        return new CharactersResponse(entity.getId(), entity.getName(), entity.getDescription());
    }
//    CharactersWithFilterResponse filter(CharacterRequest request);
}
