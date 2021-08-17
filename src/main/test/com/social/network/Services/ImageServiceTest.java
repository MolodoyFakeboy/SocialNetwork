package com.social.network.Services;

import com.social.network.Configs.Config;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = Config.class)
class ImageServiceTest {

    private InterfaceImageService imageService;

    @Autowired
    public ImageServiceTest(InterfaceImageService imageService) {
        this.imageService = imageService;
    }

    @Test
    void setImagetoPublication() throws IOException {
        FileInputStream inputFile = new FileInputStream( "D:/Project/src/main/webapp/resources/otzyvy.png");
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "otzyvy", "png", inputFile);
        imageService.setImagetoPublication(mockMultipartFile,1);
    }
}