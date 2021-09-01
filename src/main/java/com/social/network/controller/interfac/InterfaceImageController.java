package com.social.network.controller.interfac;

import com.social.network.model.Image;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface InterfaceImageController {

    ResponseEntity<Image> setImagetoPublication(MultipartFile file, int postID) throws IOException;

    ResponseEntity<Image> findImageByID(int imageID);

    ResponseEntity<List<Image>> getImageToPost(int postId);

}
