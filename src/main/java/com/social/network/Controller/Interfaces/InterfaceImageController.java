package com.social.network.Controller.Interfaces;

import com.social.network.Model.Image;
import com.social.network.PayLoad.response.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

public interface InterfaceImageController {

    ResponseEntity<Image> setImagetoPublication(MultipartFile file, int postID) throws IOException;

    ResponseEntity<Image> findImageByID(int imageID);

    ResponseEntity<List<Image>> getImageToPost(int postId);

}
