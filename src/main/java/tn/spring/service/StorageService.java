package tn.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tn.spring.dao.StorageDao;
import tn.spring.entity.DocumentData;
import tn.spring.util.documentUtil;

import java.io.IOException;
import java.util.Optional;

@Service
public class StorageService {
    @Autowired
    private StorageDao repository;

      public String uploadDocument(MultipartFile file) throws IOException {

         DocumentData documentData = repository.save(DocumentData.builder()
                  .name(file.getOriginalFilename())
                  .type(file.getContentType())
                  .documentData(documentUtil.compressImage(file.getBytes())).build());
          if (documentData != null) {
              return "file uploaded successfully : " + file.getOriginalFilename();
          }
          return null;
      }

      public byte[] downloadDocument(String fileName){
          Optional<DocumentData> dbDocumentData = repository.findByName(fileName);
          byte[] images= documentUtil.decompressImage(dbDocumentData.get().getDocumentData());
          return images;
      }
}
