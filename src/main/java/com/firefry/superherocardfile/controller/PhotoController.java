package com.firefry.superherocardfile.controller;

import com.firefry.superherocardfile.exception.NotExistPhotoException;
import com.firefry.superherocardfile.service.PhotoService;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping(value = "/photo")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @PostMapping("/uploadImage")
    public ResponseEntity<String> uploadImage(@RequestParam(value = "imageName") Optional<String> imageName,
                                              @RequestParam(value = "image") MultipartFile file){
        if(file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(photoService.addPhoto(imageName, file));
    }

    @GetMapping(value = "/{imageName}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
//    @GetMapping(value = "/{photoId}")
    @ResponseBody
    public ResponseEntity<Binary> getPhoto(@PathVariable String imageName){
//    public byte[] getPhoto(@PathVariable String imageName){
//        Optional<Binary> optionalPhoto = photoService.getPhoto(imageName);
        Binary photo = null;
        try {
            photo = photoService.getPhoto(imageName);
        } catch (NotExistPhotoException e) {
        }
//        if (photo.isEmpty()){
//            return ResponseEntity.notFound().build();
//        }
        return ResponseEntity.ok().body(photo);
//        return optionalPhoto.get().getData();
//        return photo.getData();
    }
}
