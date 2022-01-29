package com.firefry.superherocardfile.api.response;

import com.firefry.superherocardfile.entity.ComicEntity;
import lombok.Data;

@Data
public class ComicsResponse{
    private String id;
    private String title;
    private String description;

    public static ComicsResponse toComicsResponse(ComicEntity entity){
        ComicsResponse response = new ComicsResponse();
        response.setId(entity.getId());
        response.setTitle(entity.getTitle());
        response.setDescription(entity.getDescription());
        return response;
    }
    public ComicsResponse() {

    }
}
