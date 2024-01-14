package tn.spring.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.spring.controller.DownloadableResource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {
    private  final Path fileStorageLocation ;
   // private final String baseUrl;
   public FileStorageService(@Value("${upload.dir}") String uploadDir) {
       this.fileStorageLocation = Path.of(uploadDir).toAbsolutePath().normalize();
       try {
           Files.createDirectories(this.fileStorageLocation);
       } catch (IOException e) {
           throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", e);
       }
   }


    public String storeFile(MultipartFile file) {
        // Normalize le nom du fichier
        String fileName = file.getOriginalFilename();

        try {
            // Copie le fichier dans le dossier de stockage (remplace le fichier s'il existe déjà avec le même nom)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", e);
        }
    }
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists()) {
                return new InputStreamResource(resource.getInputStream());
            } else {
                throw new RuntimeException("File not found: " + fileName);
            }
        } catch (IOException ex) {
            // Ajoutez un log pour voir l'exception
            ex.printStackTrace();
            throw new RuntimeException("File not found: " + fileName, ex);
        }
    }


}
