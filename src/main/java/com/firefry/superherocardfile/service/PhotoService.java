package com.firefry.superherocardfile.service;

import com.firefry.superherocardfile.exception.NotExistPhotoException;
import com.firefry.superherocardfile.repository.PhotoRepository;
import com.firefry.superherocardfile.entity.PhotoEntity;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    public String addPhoto(Optional<String> imageName, MultipartFile file) {
        PhotoEntity photo = new PhotoEntity();
//        photo.setImageName(file.getOriginalFilename());
        photo.setImageName(imageName.orElse(file.getOriginalFilename()
                .replaceAll("(\\.jpg)|(\\." + file.getContentType().replaceAll("image/", "") + ")"
                        , "")));    //Set imageName from file without content type
        try {
            photo.setContent(new Binary(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        photo.setContentType(file.getContentType());
        photo = photoRepository.insert(photo);
        return photo.getImageName();
    }

//    public Optional<Binary> getPhoto(String imageName){
    public Binary getPhoto(String imageName) throws NotExistPhotoException {
        Optional<PhotoEntity> photo = photoRepository.findByImageName(imageName);
        if (photo.isEmpty()) {
            throw new NotExistPhotoException("Photo not found with imageName=" + imageName);
//            return Optional.ofNullable(photoRepository.findByImageName(imageName).get().getContent());
        }
        return photo.get().getContent();
    }

}
