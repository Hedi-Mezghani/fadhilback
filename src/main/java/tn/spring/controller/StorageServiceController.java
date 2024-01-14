package tn.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import tn.spring.service.StorageService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@SpringBootApplication
@RestController
@RequestMapping("/api/image")
@CrossOrigin("http://localhost:4200")
public class StorageServiceController {

        @Autowired
        private StorageService service;

    @PostMapping
    public ResponseEntity<?> uploadDocument(HttpServletRequest request) throws IOException {
        MultipartFile file = ((MultipartHttpServletRequest) request).getFile("document");
        if (file != null) {
            String uploadImage = service.uploadDocument(file);
            return ResponseEntity.status(HttpStatus.OK).body(uploadImage);
        } else {
            // Gérez le cas où le fichier n'est pas présent dans la requête
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Le fichier 'document' est requis.");
        }
    }

   @GetMapping("/{fileName}")
        public ResponseEntity<?> downloadDocument(@PathVariable String fileName){
            byte[] imageData=service.downloadDocument(fileName);
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(imageData);

        }
}
