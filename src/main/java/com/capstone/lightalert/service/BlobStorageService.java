package com.capstone.lightalert.service;


import com.azure.storage.blob.*;
import com.capstone.lightalert.model.Users;
import com.capstone.lightalert.model.Videos;
import com.capstone.lightalert.repository.UserRepository;
import com.capstone.lightalert.repository.VideosRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class BlobStorageService {
    @Value("${spring.cloud.azure.storage.blob.container-name}")
    private String containerName;
    @Value("${azure.blob-storage.connection-string}")
    private String connectionString;

    private UserRepository userRepository;

    private final VideosRepository videosRepository;

    public BlobStorageService(VideosRepository videosRepository,UserRepository userRepository) {
        this.videosRepository = videosRepository;
        this.userRepository = userRepository;
    }

    public String uploadVideo(MultipartFile file, String systemId) throws IOException {

        String blobUrl = uploadFileToBlobStorage(file);


        Videos video = new Videos();
        video.setVideoURL(blobUrl);
        video.setUploadDate(new java.sql.Date(System.currentTimeMillis()));

        Users user = userRepository.findBySystemId(systemId);
        if (user != null) {
            video.setUser(user);
        } else {
            throw new IllegalArgumentException("User not found for systemId: " + systemId);
        }


        videosRepository.save(video);
        return blobUrl;
    }

    private String uploadFileToBlobStorage(MultipartFile file) throws IOException {

        String fileName = file.getOriginalFilename();

        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);


        BlobClient blobClient = containerClient.getBlobClient(fileName);
        blobClient.upload(file.getInputStream(), file.getSize(), true);

        return blobClient.getBlobUrl();
    }
}
