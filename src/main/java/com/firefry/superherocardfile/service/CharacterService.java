package com.firefry.superherocardfile.service;

import com.firefry.superherocardfile.api.request.CharacterRequest;
import com.firefry.superherocardfile.api.response.CharactersResponse;
import com.firefry.superherocardfile.api.response.ComicsResponse;
import com.firefry.superherocardfile.entity.CharacterEntity;
import com.firefry.superherocardfile.exception.NotFoundCharacterEntityException;
import com.firefry.superherocardfile.repository.CharacterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@ConfigurationProperties(prefix = "application")
public class CharacterService {

    @Autowired
    private CharacterRepository characterRepository;

    @Autowired
    private ComicsService comicsService;

    public CharactersResponse getById(String characterId) throws NotFoundCharacterEntityException{
        Optional<CharacterEntity> optionalCharacterEntity = characterRepository.findById(characterId);
        if (optionalCharacterEntity.isEmpty()){
            throw new NotFoundCharacterEntityException("Character not found with id=" + characterId);
        }
        return CharactersResponse.toCharactersResponse(optionalCharacterEntity.get());
    }

    public Page<CharactersResponse> getCharactersPage(Optional<Integer> page
                                                    , Optional<Integer> size
                                                    , Optional<String> sortBy) {
        return characterRepository.findAll(PageRequest.of(page.orElse(0)
                , size.orElse(3)
                , Sort.Direction.ASC, sortBy.orElse("name"))).map(CharactersResponse::toCharactersResponse);
    }

    public CharactersResponse addCharacter(CharacterRequest request) {
        Optional<CharacterEntity> optionalCharacterEntity = characterRepository.findByName(request.getName());
        if (optionalCharacterEntity.isPresent()){
            CharacterEntity entity = optionalCharacterEntity.get();
            entity.setImageName(request.getName());
            entity.setDescription(request.getDescription());
            entity.setComicsList(request.getComicsList());
            return CharactersResponse.toCharactersResponse(characterRepository.save(entity));
        }
        CharacterEntity entity = new CharacterEntity(request.getName(),
                                                    request.getDescription(),
                                                    request.getComicsList());
        return CharactersResponse.toCharactersResponse(characterRepository.save(entity));
    }

    public void deleteById(String characterId) {
        characterRepository.deleteById(characterId);
    }

    public Page<ComicsResponse> getComicsPageByCharacterId(String characterId,
                                                           Optional<Integer> page,
                                                           Optional<Integer> size,
                                                           Optional<String> sortBy) throws NotFoundCharacterEntityException {
        Optional<CharacterEntity> optionalCharacter =characterRepository.findById(characterId);
        if (optionalCharacter.isEmpty()){
            throw new NotFoundCharacterEntityException("Not found character with id=" + characterId);
        }
        return comicsService.getComicsPageByCharacter(optionalCharacter.get().getName(), page, size, sortBy);
    }

    public Page<CharactersResponse> getCharactersPageByComic(String title,
                                                             Optional<Integer> page,
                                                             Optional<Integer> size,
                                                             Optional<String> sortBy) {
        return characterRepository.findByComicsList(title, PageRequest.of(page.orElse(0),
                size.orElse(3),
                Sort.Direction.ASC, sortBy.orElse("name"))).map(CharactersResponse::toCharactersResponse);
    }
}

