package com.firefry.superherocardfile.service;

import com.firefry.superherocardfile.api.request.CharacterRequest;
import com.firefry.superherocardfile.api.response.CharactersResponse;
import com.firefry.superherocardfile.entity.CharacterEntity;
import com.firefry.superherocardfile.exception.NotFoundCharacterEntityException;
import com.firefry.superherocardfile.repository.CharacterRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CharacterService {
    @Autowired
    private CharacterRepository characterRepository;

    public CharactersResponse getById(String characterId) throws NotFoundCharacterEntityException{
        Optional<CharacterEntity> entity = characterRepository.findById(characterId);
        if (entity.isEmpty()){
            throw new NotFoundCharacterEntityException("Character not found with id=" + characterId);
        }
        return CharactersResponse.toCharactersResponse(entity.get());
    }

    public List<CharactersResponse> getCharactersList() {
        List<CharacterEntity> characterEntityList = characterRepository.findAll();

        return characterEntityList.stream().map(CharactersResponse::toCharactersResponse)
                .collect(Collectors.toList());
    }

    public CharactersResponse create(CharacterRequest request) {
        CharacterEntity entity = new CharacterEntity(request.getName(), request.getDescription());
        characterRepository.save(entity);
        return CharactersResponse.toCharactersResponse(entity);
    }
}
