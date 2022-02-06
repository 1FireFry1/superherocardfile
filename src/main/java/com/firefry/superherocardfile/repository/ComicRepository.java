package com.firefry.superherocardfile.repository;

import com.firefry.superherocardfile.entity.ComicEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComicRepository extends MongoRepository<ComicEntity, String> {

    Page<ComicEntity> findByCharactersList(String characterName, Pageable pageable);

    Optional<ComicEntity> findByTitle(String title);
}
