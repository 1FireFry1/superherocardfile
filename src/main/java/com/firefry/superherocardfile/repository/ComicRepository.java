package com.firefry.superherocardfile.repository;

import com.firefry.superherocardfile.entity.ComicEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComicRepository extends MongoRepository<ComicEntity, String> {

}
