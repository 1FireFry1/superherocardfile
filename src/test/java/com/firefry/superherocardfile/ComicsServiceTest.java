package com.firefry.superherocardfile;

import com.firefry.superherocardfile.api.response.ComicsResponse;
import com.firefry.superherocardfile.entity.ComicEntity;
import com.firefry.superherocardfile.exception.NotFoundComicEntityException;
import com.firefry.superherocardfile.repository.ComicRepository;
import com.firefry.superherocardfile.service.ComicsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ComicsServiceTest {

    @Autowired
    private ComicsService comicsService;

    @MockBean
    private ComicRepository comicRepository;

    @Test
    public void notExistComicId(){
        assertThrows(NotFoundComicEntityException.class, () -> comicsService.getById("nvhdwkdih"));
    }

    @Test
    public void getExistComicId(){
        ComicEntity comicEntity = new ComicEntity();
        comicEntity.setId("1");
        comicEntity.setTitle("11");
        comicEntity.setDescription("111");

        when(comicRepository.findById("1")).thenReturn(java.util.Optional.of(comicEntity));

        ComicsResponse expected = ComicsResponse.toComicsResponse(comicEntity);
        ComicsResponse actual = null;
        try {
            actual = comicsService.getById("1");
        } catch (NotFoundComicEntityException e) {
            e.printStackTrace();
        }

        assertEquals(expected, actual);
    }

}