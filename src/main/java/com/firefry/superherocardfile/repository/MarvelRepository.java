package com.firefry.superherocardfile.repository;

import com.firefry.superherocardfile.domain.MarvelEntity;

import java.util.List;

public interface MarvelRepository {
    MarvelEntity getById(String id);
    List<MarvelEntity> getList();
    void add(MarvelEntity marvelEntity);
}
