package com.social.network.controller;

import com.social.network.controller.interfac.InterfaceImageController;
import com.social.network.model.Image;
import com.social.network.payLoad.response.MessageResponse;
import com.social.network.service.interfac.InterfaceImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class ImageController implements InterfaceImageController {

    private InterfaceImageService imageService;

    @Autowired
    public ImageController(InterfaceImageService imageService) {
        this.imageService = imageService;
    }

    @Override
    @PostMapping("image/{postID}")
    public ResponseEntity<Image> setImagetoPublication(@RequestParam("file") MultipartFile file, @PathVariable int postID) {
        Image image = imageService.setImagetoPublication(file, postID);
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

    @Override
    @GetMapping("image/{imageID}")
    public ResponseEntity<Image> findImageByID(@PathVariable int imageID) {
        Image image = imageService.findImageByID(imageID);
        return new ResponseEntity<>(image, HttpStatus.OK);
    }

    @Override
    @GetMapping("images/{postId}")
    public ResponseEntity<List<Image>> getImageToPost(@PathVariable int postId) {
        List<Image> images = imageService.getImageToPost(postId);
        return new ResponseEntity<>(images,HttpStatus.OK);
    }

    @Override
    @DeleteMapping("images/{postID}")
    public ResponseEntity<MessageResponse> deleteImageFromPost(@PathVariable int postID) {
        imageService.deleteImageFromPost(postID);
        return new ResponseEntity<>(new MessageResponse("Фото удалены"),HttpStatus.OK);
    }
}
