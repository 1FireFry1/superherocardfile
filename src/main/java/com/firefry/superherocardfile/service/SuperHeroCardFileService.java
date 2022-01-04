package com.firefry.superherocardfile.service;

import com.firefry.superherocardfile.api.response.MarvelResponse;

import java.util.List;

public interface SuperHeroCardFileService {
    MarvelResponse getById(String characterId);
    List<MarvelResponse> getFirst(int amount);
//    MarvelWithFilterResponse filter(MarvelRequest request);
}
