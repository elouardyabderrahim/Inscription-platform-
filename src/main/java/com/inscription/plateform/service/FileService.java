package com.inscription.plateform.service;


import com.inscription.plateform.entity.FormFile;
import com.inscription.plateform.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public FormFile Upload(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        FormFile formFile = new FormFile(fileName, file.getContentType());

        return fileRepository.save(formFile);
    }

    public FormFile getFile(String id) {
        return fileRepository.findById(id).get();
    }

    public Stream<FormFile> getAllFiles() {
        return fileRepository.findAll().stream();
    }
}



