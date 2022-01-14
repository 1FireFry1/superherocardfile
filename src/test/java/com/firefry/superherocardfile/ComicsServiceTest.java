//package com.firefry.superherocardfile;
//
//import com.firefry.superherocardfile.api.response.ComicsResponse;
//import com.firefry.superherocardfile.entity.ComicEntity;
//import com.firefry.superherocardfile.exception.NotFoundComicEntityException;
//import com.firefry.superherocardfile.repository.ComicRepository;
//import com.firefry.superherocardfile.service.ComicsService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//
//@SpringBootTest
//public class ComicsServiceTest {
//
//    @Autowired
//    private ComicsService comicsService;
//
//    @MockBean
//    private ComicRepository comicRepository;
//
//    @Test
//    public void notExistComicId(){
//        when(comicRepository.getById(anyString())).thenThrow(NotFoundComicEntityException.class);
//        assertThrows(NotFoundComicEntityException.class, () -> comicsService.getById("nvhdwkdih"));
//    }
//
//    @Test
//    public void getExistComicId(){
//        ComicEntity comicEntity = new ComicEntity();
//        comicEntity.setId("1");
//        comicEntity.setName("11");
//        comicEntity.setDescription("111");
//
//        when(comicRepository.getById("1")).thenReturn(comicEntity);
//
//        ComicsResponse expected = new ComicsResponse("1", "11", "111");
//        ComicsResponse actual = comicsService.getById("1");
//
//        assertEquals(expected, actual);
//    }
//
//}
