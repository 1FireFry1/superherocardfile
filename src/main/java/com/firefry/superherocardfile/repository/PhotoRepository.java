package com.firefry.superherocardfile.repository;

import com.firefry.superherocardfile.entity.PhotoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PhotoRepository extends MongoRepository<PhotoEntity, String> {
    Optional<PhotoEntity> findByImageName(String imageName);
}
