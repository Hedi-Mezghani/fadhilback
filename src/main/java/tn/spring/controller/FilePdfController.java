package tn.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.spring.entity.FileUpload;
import tn.spring.service.FileUploadService;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
@RequestMapping("/api/files")
@CrossOrigin("http://localhost:4200")
public class FilePdfController {
    private final FileUploadService fileUploadService;
    @Value("${upload.dir}")
    private String uploadDir;

    @Autowired
    public FilePdfController(FileUploadService fileUploadService) {
        this.fileUploadService = fileUploadService;
    }

    @PostMapping("/upload")
    public ResponseEntity<FileUpload> uploadPdfFile(@RequestParam("file") MultipartFile file) {
        // Obtenez l'URL de téléchargement du fichier en utilisant la propriété upload.dir
        String fileDownloadUri = uploadDir + "/" + file.getOriginalFilename();

        // Appelez la méthode uploadFile avec les deux arguments
        FileUpload uploadedFile = fileUploadService.uploadFile(file, fileDownloadUri);

        return ResponseEntity.ok(uploadedFile);
    }
   /* @GetMapping("/download/{fileName:.+}")
    public ResponseEntity<Resource> downloadPdfFile(@PathVariable String fileName, HttpServletRequest request) {
        try {
            // Chargez le fichier en tant que Resource
            Resource resource = fileUploadService.loadFileAsResource(fileName);

            // Obtenez le type de média du fichier
            String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());

            // Si le type de média n'est pas défini, définissez-le comme application/octet-stream
            if (contentType == null) {
                contentType = "application/octet-stream";
            }

            // Définissez les en-têtes de la réponse
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType(contentType));
            headers.setContentDispositionFormData("attachment", fileName);

            // Créez un InputStreamResource à partir du fichier pour l'inclure dans le corps de la réponse
            InputStreamResource inputStreamResource = new InputStreamResource(resource.getInputStream());

            // Retournez la réponse avec le corps contenant le fichier à télécharger
            return new ResponseEntity<>(inputStreamResource, headers, HttpStatus.OK);
        } catch (IOException e) {
            // Gérez l'exception ici, par exemple, en journalisant l'erreur
            e.printStackTrace(); // Vous pouvez remplacer ceci par votre logique de gestion d'erreur
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }*/



}
