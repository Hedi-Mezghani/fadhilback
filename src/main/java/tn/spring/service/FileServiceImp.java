package tn.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.dao.FileDao;
import tn.spring.entity.File;

import java.util.List;
import java.util.Optional;

@Service
public class FileServiceImp implements FileService  {
    private final FileDao fileDao;

    @Autowired
    public FileServiceImp(FileDao fileDao){
        this.fileDao =fileDao;
    }

    @Override
    public File createFile(File file) {
        return fileDao.save(file);
    }

    @Override
    public File getFileById(Long id) {
        Optional<File>optionalFile = fileDao.findById(id);
        return optionalFile.orElse(null);
    }

    @Override
    public List<File> getAllFiles() {
        return fileDao.findAll();
    }

    @Override
    public File updateFile(Long id, File updatedFile) {
        if(fileDao.existsById(id)){
            updatedFile.setId(id);
            return fileDao.save(updatedFile);
        }
        return null;
    }

    @Override
    public boolean deleteFile(Long id) {
        fileDao.deleteById(id);

        return false;
    }
}
