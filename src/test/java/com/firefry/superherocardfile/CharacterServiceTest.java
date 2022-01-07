package com.firefry.superherocardfile;

import com.firefry.superherocardfile.api.response.CharactersResponse;
import com.firefry.superherocardfile.domain.CharacterEntity;
import com.firefry.superherocardfile.exception.NotFoundCharacterEntityException;
import com.firefry.superherocardfile.repository.CharacterRepository;
import com.firefry.superherocardfile.service.CharacterService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CharacterServiceTest {

    @Autowired
    private CharacterService characterService;

    @MockBean
    private CharacterRepository repository;

    @Test
    public void notExistCharacterId(){
        when(repository.getById(anyString())).thenThrow(NotFoundCharacterEntityException.class);
        assertThrows(NotFoundCharacterEntityException.class, () -> characterService.getById("bhcbscsbh"));
    }

    @Test
    public void getExistCharacterId(){
        CharacterEntity entity = new CharacterEntity();
        entity.setId("2");
        entity.setName("Character");
        entity.setDescription("Character description");

        when(repository.getById("2")).thenReturn(entity);

        CharactersResponse expected =
                new CharactersResponse("2", "Character", "Character description");
        CharactersResponse actual = characterService.getById("2");

        assertEquals(expected, actual);
    }
}
