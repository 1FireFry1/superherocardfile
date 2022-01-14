package com.firefry.superherocardfile.repository;

import com.firefry.superherocardfile.entity.CharacterEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends MongoRepository<CharacterEntity, String> {

}
