package tn.spring.service;

import tn.spring.entity.CraDataPdf;
import tn.spring.entity.File;

import java.util.List;

public interface CraDataUploadService {
    CraDataPdf createFile(CraDataPdf craDataPdf);
    CraDataPdf getFileById(Long id);
    List<CraDataPdf> getAllFiles();
    CraDataPdf updateFile(Long id, CraDataPdf craDataPdf);
    boolean deleteFile(Long id);
}
