package com.social.network.Services;

import com.social.network.Model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface InterfaceImageService {

    Image setImagetoPublication(MultipartFile file, int postID) throws IOException;
}
