package com.social.network.service.interfac;

import com.social.network.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface InterfaceImageService {

    Image setImagetoPublication(MultipartFile file, int postID);

    Image findImageByID(int imageID);

    List<Image> getImageToPost(int postId);

}
