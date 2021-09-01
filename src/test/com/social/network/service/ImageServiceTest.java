package com.social.network.service;

import com.social.network.Config.TestConfig;
import com.social.network.model.Image;
import com.social.network.service.interfac.InterfaceImageService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestConfig.class)
class ImageServiceTest {

    private InterfaceImageService imageService;

    @Autowired
    public ImageServiceTest(InterfaceImageService imageService) {
        this.imageService = imageService;
    }

    @Test
    void setImagetoPublication() throws IOException {
        FileInputStream inputFile = new FileInputStream("D:/Project/src/main/webapp/resources/otzyvy.png");
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "otzyvy", "png", inputFile);
        Image image = imageService.setImagetoPublication(mockMultipartFile, 1);

        Assertions.assertNotNull(image.getPublication());
    }

    @Test
    void getImageToPost() {
        List<Image> images = imageService.getImageToPost(1);

        Assertions.assertNotNull(images);
    }

}