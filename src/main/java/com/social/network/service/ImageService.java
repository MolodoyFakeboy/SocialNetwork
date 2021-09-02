package com.social.network.service;

import com.social.network.dao.GenericDao;
import com.social.network.exceptions.ImageNotFoundException;
import com.social.network.model.Image;
import com.social.network.model.Publication;
import com.social.network.service.interfac.InterfaceImageService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@Service
@Transactional
public class ImageService implements InterfaceImageService {

    private GenericDao<Image> imageDao;

    private GenericDao<Publication> publicationDao;

    private Logger log;

    @Autowired
    public ImageService(GenericDao<Image> imageDao, GenericDao<Publication> publicationDao) {
        this.imageDao = imageDao;
        this.publicationDao = publicationDao;
        log = LogManager.getLogger(ImageService.class);
    }

    @Override
    public Image setImagetoPublication(MultipartFile file, int postID) {
        Publication publication = publicationDao.find(postID);
        Image image = new Image();
        try {
            image.setPhoto(compressBytes(file.getBytes()));
            image.setName(file.getName());
            image.setPublication(publication);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        log.info("Uploading image to Publication {}", publication.getId());
        imageDao.add(image);
        return image;
    }


    @Override
    public List<Image> getImageToPost(int postId) {
        Publication publication = publicationDao.find(postId);
        List<Image>images = new ArrayList<>((publication.getImages()));
        images.forEach(image -> decompressBytes(image.getPhoto()));
        return images;
    }

    @Override
    public Image findImageByID(int imageID) {
        Image image = imageDao.find(imageID);
        if (image != null) {
            return image;
        } else {
            throw new ImageNotFoundException("No Image with Such ID: " + imageID);
        }
    }

    @Override
    public void deleteImageFromPost(int postID){
        Publication publication = publicationDao.find(postID);
        List<Image> images = new ArrayList<>(publication.getImages());
        images.forEach(image -> imageDao.delete(image.getIdImage()));
    }

    //cжатие фотки
    private byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
            log.error("Cannot compress Bytes");
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    //Расжатие фотки
    private byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException | DataFormatException e) {
            log.error("Cannot decompress Bytes");
        }
        return outputStream.toByteArray();
    }

}
