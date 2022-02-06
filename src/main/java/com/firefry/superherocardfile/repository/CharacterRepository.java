package com.firefry.superherocardfile.repository;

import com.firefry.superherocardfile.entity.CharacterEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CharacterRepository extends MongoRepository<CharacterEntity, String> {

    Page<CharacterEntity> findByComicsList(String title, Pageable pageable);

    Optional<CharacterEntity> findByName(String name);
}
