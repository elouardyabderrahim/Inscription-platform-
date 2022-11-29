package com.inscription.plateform.service;


import com.inscription.plateform.entity.FormFile;
import com.inscription.plateform.repository.FileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    private final Path root = Paths.get("uploads");
//changed


    public FormFile Upload(MultipartFile file) throws IOException {
        //String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        FormFile formFile = new FormFile(fileName, file.getContentType());


        LinkOption[] linkOptions = new LinkOption[]{LinkOption.NOFOLLOW_LINKS};

       try{ if (Files.notExists(root, linkOptions)) {

            Files.createDirectory(root);

        }
       } catch (IOException e) {
           throw new RuntimeException("Could not initialize folder for upload!");
       }
        try {
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
        return fileRepository.save(formFile);



    }

    public Resource getFile(String id) {


        try {
            Path file = root.resolve(id);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
        //return fileRepository.findById(id).get();
    }



    public Stream<FormFile> getAllFiles() {
        return fileRepository.findAll().stream();
    }
}



