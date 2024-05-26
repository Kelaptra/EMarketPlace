package com.example.demo2.services;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class PhotoService {

    private static final String FOLDER_NAME = "photos";
    private static final String photo_extension = ".jpg";
    private final Path ROOT_FOLDER = Paths.get(FOLDER_NAME);
    private static final String photoNameStartingWith = "photo";

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(ROOT_FOLDER);
        } catch (IOException e) {
            throw new RuntimeException("Couldn't initialize folder for photos!");
        }
    }

    public String storePhoto(MultipartFile file, String photoNameUniquePart) {
        String photoUrl = photoNameStartingWith + photoNameUniquePart + photo_extension;
        try {
            Files.copy(file.getInputStream(), ROOT_FOLDER.resolve(photoUrl));
            return photoUrl;
        } catch (IOException e) {
            throw new RuntimeException("Couldn't store: " + photoUrl, e);
        }
    }
}
