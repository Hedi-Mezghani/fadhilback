package tn.spring.service;

import org.springframework.stereotype.Service;
import tn.spring.dao.CraDataUploadDao;
import tn.spring.entity.CraDataPdf;
import tn.spring.entity.File;

import java.util.List;
import java.util.Optional;
@Service
public class CraDataUploadServiceImp implements CraDataUploadService{
    private final CraDataUploadDao craDataPdfDao;

    public CraDataUploadServiceImp(CraDataUploadDao craDataPdfDao) {
        this.craDataPdfDao = craDataPdfDao;
    }


    @Override
    public CraDataPdf createFile(CraDataPdf craDataPdf) {

        return craDataPdfDao.save(craDataPdf);
    }

    @Override
    public CraDataPdf getFileById(Long id) {
        Optional<CraDataPdf> optionalFile = craDataPdfDao.findById(id);
        return optionalFile.orElse(null);
    }

    @Override
    public List<CraDataPdf> getAllFiles() {
        return craDataPdfDao.findAll();
    }

    @Override
    public CraDataPdf updateFile(Long id, CraDataPdf updatedFile) {
        if(craDataPdfDao.existsById(id)){
            updatedFile.setId(id);
            return craDataPdfDao.save(updatedFile);
        }
        return null;
    }

    @Override
    public boolean deleteFile(Long id) {
        craDataPdfDao.deleteById(id);

        return false;
    }
}
