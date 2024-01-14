package tn.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tn.spring.entity.FileUpload;

import java.io.IOException;


@Service
public class FileUploadService {
    private final FileStorageService fileStorageService;

    @Autowired
    public FileUploadService(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }


    public FileUpload uploadFile(MultipartFile file, String fileDownloadUri) {


        FileUpload response = new FileUpload();
        response.setFileName(file.getOriginalFilename());
        response.setFileDownloadUri(fileDownloadUri);
        response.setFileType(file.getContentType());
        response.setSize(file.getSize());

        return response;
    }


    public Resource loadFileAsResource(String fileName) throws IOException {
        return fileStorageService.loadFileAsResource(fileName);
    }

}
