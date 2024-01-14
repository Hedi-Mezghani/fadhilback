package tn.spring.service;

import tn.spring.entity.File;

import java.util.List;

public interface FileService {
    File createFile(File file);
    File getFileById(Long id);
    List<File> getAllFiles();
    File updateFile(Long id, File file);
    boolean deleteFile(Long id);
}
