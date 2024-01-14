package tn.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.spring.dao.AddPdfDao;
import tn.spring.dao.DossierRepository;
import tn.spring.entity.CraData;
import tn.spring.service.CraDataServiceImp;
import tn.spring.service.CraDataUploadServiceImp;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.stream.Collectors;

import static java.nio.file.Files.copy;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

@SpringBootApplication
@RestController
@RequestMapping("/api/cra")
@CrossOrigin("http://localhost:4200")
public class CraDataController {
    private final CraDataServiceImp craDataServiceIpm;
    private final CraDataUploadServiceImp craDataUploadServiceImp;
    public static final String DIRECTORY = System.getProperty("user.home") + "/Downloads/CraDataUploads";
    @Autowired
    private AddPdfDao addPdfDao;
    @Autowired
    private DossierRepository dossierRepository;
    public CraDataController(CraDataServiceImp craDataServiceIpm, CraDataUploadServiceImp craDataUploadServiceImp) {
        this.craDataServiceIpm = craDataServiceIpm;
        this.craDataUploadServiceImp = craDataUploadServiceImp;
    }

    // Endpoint pour obtenir tous les cra
    @GetMapping
    public ResponseEntity<List<CraData>> getAllCraDatas() {
        List<CraData> craDatas = craDataServiceIpm.getAllCraDatas();
        return new ResponseEntity<>(craDatas, HttpStatus.OK);
    }

    // Endpoint pour obtenir un cra par ID
    @GetMapping("/{id}")
    public ResponseEntity<CraData> getCraDataById(@PathVariable Long id) {
        Optional<CraData> craData = craDataServiceIpm.getCraDataById(id);
        if (craData.isPresent()) {
            return new ResponseEntity<>(craData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint pour créer un nouveau cra
    @PostMapping
    public ResponseEntity<CraData> createCraData(@RequestBody CraData craData) {
        CraData createdCraData = craDataServiceIpm.createCaraData(craData);
        return new ResponseEntity<>(createdCraData, HttpStatus.CREATED);
    }

    // Endpoint pour mettre à jour un cra par ID
    @PutMapping("/{id}")
    public ResponseEntity<CraData> updateCraData(@PathVariable Long id, @RequestBody CraData updateCra) {
        CraData updatedCraData = craDataServiceIpm.updateCraData(id, updateCra);
        if (updatedCraData != null) {
            return new ResponseEntity<>(updatedCraData, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint pour supprimer un cra par ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCraData(@PathVariable Long id) {
        craDataServiceIpm.deleteCraData(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/byDate")
    public ResponseEntity<List<CraData>> getCraDataByDate(@RequestParam("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        List<CraData> craDataByDate = craDataServiceIpm.findByDate(date);
        if (craDataByDate.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(craDataByDate, HttpStatus.OK);
        }
    }

    @GetMapping("/byUserId")
    public ResponseEntity<List<CraData>> getCraDataByUserId(@RequestParam("userId") Long userId) {
        List<CraData> craDataByUserId = craDataServiceIpm.findByUserId(userId);
        if (craDataByUserId.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(craDataByUserId, HttpStatus.OK);
        }
    }

    @GetMapping("/byYearAndMonth")
    public ResponseEntity<List<CraData>> getCraDataByYearAndMonth(
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "month", required = false) Integer month
    ) {
        List<CraData> craDataFiltered;

        if (year != null && month != null) {
            // Recherche par année et mois
            craDataFiltered = craDataServiceIpm.findByYearAndMonth(year, month);
        } else if (year != null) {
            // Recherche par année uniquement
            craDataFiltered = craDataServiceIpm.findByYear(year);
        } else if (month != null) {
            // Recherche par mois uniquement
            craDataFiltered = craDataServiceIpm.findByMonth(month);
        } else {
            // Aucun paramètre spécifié
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (craDataFiltered.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(craDataFiltered, HttpStatus.OK);
        }
    }

    @GetMapping("/byYear")
    public ResponseEntity<List<CraData>> getCraDataByYear(
            @RequestParam(value = "year", required = false) Integer year
    ) {
        if (year != null) {
            List<CraData> craData = craDataServiceIpm.findByYear(year);

            return new ResponseEntity<>(craData, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(path = "/craDatapdf", produces =
            MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> venteReport()
            throws IOException {
        List<CraData> craData = craDataServiceIpm.getAllCraDatas();

        ByteArrayInputStream bis = craDataServiceIpm.craPDFReport(craData);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=ventes.pdf");
        return ResponseEntity.ok().headers(headers).contentType
                        (MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    @PostMapping("/upload")
    public ResponseEntity<List<String>> uploadFiles(
            @RequestParam("files") List<MultipartFile> multipartFiles,
            @RequestParam("userId") Long userId,
            @RequestHeader("Content-Type") String contentType) throws IOException, NoSuchAlgorithmException{

        List<String> filenames = new ArrayList<>();
        String userDirectory = "user" + userId;
        Path userDirectoryPath = Paths.get(DIRECTORY, userDirectory).toAbsolutePath().normalize();
        Files.createDirectories(userDirectoryPath);

        for (MultipartFile file : multipartFiles) {
            String filename = StringUtils.cleanPath(file.getOriginalFilename());
            Path fileStorage = userDirectoryPath.resolve(filename).normalize();
            copy(file.getInputStream(), fileStorage, REPLACE_EXISTING);

            // Convertir le contenu du fichier en base64
            byte[] fileBytes = file.getBytes();
            String base64String = Base64.getEncoder().encodeToString(fileBytes);

            // Calculer l'empreinte de hachage SHA-256 du contenu du fichier
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(fileBytes);
            String hashString = Base64.getUrlEncoder().encodeToString(hash);

            // Ajouter la partie du hachage au nom du fichier
            String filenameWithHash = filename.replace(".", "_" + hashString.substring(0, 8) + ".");
            Path renamedFile = userDirectoryPath.resolve(filenameWithHash).normalize();
            Files.move(fileStorage, renamedFile);

            filenames.add(userDirectory + "/" + filenameWithHash);
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
