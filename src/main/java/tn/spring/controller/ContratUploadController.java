package tn.spring.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.spring.dao.AddPdfDao;
import tn.spring.dao.DossierRepository;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.file.Files.copy;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

@RestController
@RequestMapping("/api/contratupload")
@CrossOrigin("http://localhost:4200")
@Slf4j
public class ContratUploadController {
    public static final String DIRECTORY = System.getProperty("user.home") + "/Downloads/contrat";
    @Autowired
    private AddPdfDao addPdfDao;
    @Autowired
    private DossierRepository dossierRepository;


    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "syfax",
            "api_key", "836798284526591",
            "api_secret", "TfEOEiQuibpTmKfFST_bF-AOJgw"));


    @PostMapping("/upload")
    public ResponseEntity<List<String>> uploadFiles(@RequestParam("files") List<MultipartFile> multipartFiles,
                                                    @RequestParam("user_id") Long userId) throws IOException {
        List<String> filenames = new ArrayList<>();
        String userDirectory = "user_" + userId; // Créez un répertoire spécifique pour chaque utilisateur
        Path userDirectoryPath = Paths.get(DIRECTORY, userDirectory).toAbsolutePath().normalize();
        Files.createDirectories(userDirectoryPath); // Créez le répertoire s'il n'existe pas déjà

        for (MultipartFile file : multipartFiles) {
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            Path fileStorage = userDirectoryPath.resolve(filename).normalize();
            copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);
            filenames.add(userDirectory + "/" + filename);
        }
        return ResponseEntity.ok().body(filenames);
    }

    @GetMapping("/download/{user_id}/{filename:.+}")
    public ResponseEntity<Resource> downloadFiles(@PathVariable("user_id") Long userId,
                                                  @PathVariable("filename") String filename) throws IOException {
        String userDirectory = "user_" + userId;
        Path filePath = Paths.get(DIRECTORY, userDirectory).toAbsolutePath().normalize().resolve(filename);

        if (!Files.exists(filePath)) {
            throw new FileNotFoundException(filename + " was not found on the server");
        }

        Resource resource = new UrlResource(filePath.toUri());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("File-Name", filename);
        httpHeaders.add(CONTENT_DISPOSITION, "attachment;File-Name=" + resource.getFilename());

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(Files.probeContentType(filePath)))
                .headers(httpHeaders)
                .body(resource);
    }
    @GetMapping("/downloadAll/{user_id}")
    public ResponseEntity<List<String>> downloadAllFiles(@PathVariable("user_id") Long userId) throws IOException {
        String userDirectory = "user_" + userId;
        Path userDirectoryPath = Paths.get(DIRECTORY, userDirectory).toAbsolutePath().normalize();

        if (!Files.exists(userDirectoryPath) || !Files.isDirectory(userDirectoryPath)) {
            throw new FileNotFoundException("Le répertoire de l'utilisateur n'a pas été trouvé");
        }

        List<String> filenames = Files.list(userDirectoryPath)
                .filter(Files::isRegularFile)
                .map(Path::getFileName)
                .map(Path::toString)
                .collect(Collectors.toList());

        return ResponseEntity.ok().body(filenames);
    }

}
