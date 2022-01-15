package com.firefry.superherocardfile;

import com.firefry.superherocardfile.api.response.CharactersResponse;
import com.firefry.superherocardfile.entity.CharacterEntity;
import com.firefry.superherocardfile.exception.NotFoundCharacterEntityException;
import com.firefry.superherocardfile.repository.CharacterRepository;
import com.firefry.superherocardfile.service.CharacterService;
import org.junit.jupiter.api.Test;
import org.mockito.exceptions.misusing.UnfinishedStubbingException;
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
        assertThrows(NotFoundCharacterEntityException.class, () -> characterService.getById("bhcbscsbh"));
    }

    @Test
    public void getExistCharacterId(){
        CharacterEntity entity = new CharacterEntity();
        entity.setId("2");
        entity.setName("Character");
        entity.setDescription("Character description");

        when(repository.findById("2")).thenReturn(java.util.Optional.of(entity));

        CharactersResponse expected =
                CharactersResponse.toCharactersResponse(entity);
        CharactersResponse actual = null;
        try {
            actual = characterService.getById("2");
        } catch (NotFoundCharacterEntityException e) {
            e.printStackTrace();
        }

        assertEquals(expected, actual);
    }
}
