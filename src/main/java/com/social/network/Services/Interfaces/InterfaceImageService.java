package com.social.network.Services.Interfaces;

import com.social.network.Model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface InterfaceImageService {

    Image setImagetoPublication(MultipartFile file, int postID) throws IOException;

    Image findImageByID(int imageID);

    List<Image> getImageToPost(int postId);
}
