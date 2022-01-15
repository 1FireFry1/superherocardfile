package com.firefry.superherocardfile.service;

import com.firefry.superherocardfile.api.request.CharacterRequest;
import com.firefry.superherocardfile.api.response.CharactersResponse;
import com.firefry.superherocardfile.entity.CharacterEntity;
import com.firefry.superherocardfile.exception.NotFoundCharacterEntityException;
import com.firefry.superherocardfile.repository.CharacterRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public Page<CharactersResponse> getCharactersPage(Optional<Integer> page, Optional<Integer> size, Optional<String> sortBy) {
        Page<CharactersResponse> charactersResponsePage = characterRepository.findAll(PageRequest.of(page.orElse(0)
                , size.orElse(3)
                , Sort.Direction.ASC, sortBy.orElse("name"))).map(CharactersResponse::toCharactersResponse);
        return charactersResponsePage;
    }

    public CharactersResponse create(CharacterRequest request) {
        CharacterEntity entity = new CharacterEntity(request.getName(), request.getDescription());
        characterRepository.save(entity);
        return CharactersResponse.toCharactersResponse(entity);
    }
}
