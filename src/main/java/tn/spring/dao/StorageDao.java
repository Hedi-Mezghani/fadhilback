package tn.spring.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.spring.entity.DocumentData;

import java.util.Optional;

public interface StorageDao extends JpaRepository<DocumentData,Long> {

        Optional<DocumentData> findByName(String fileName);
    }

