package com.firefry.superherocardfile.service;

import com.firefry.superherocardfile.api.request.MarvelRequest;
import com.firefry.superherocardfile.api.response.MarvelResponse;

import java.util.List;

public interface SuperHeroCardFileService {
    MarvelResponse getById(String characterId);
    List<MarvelResponse> getFirst();
    MarvelResponse create(MarvelRequest marvelRequest);
//    MarvelWithFilterResponse filter(MarvelRequest request);
}
